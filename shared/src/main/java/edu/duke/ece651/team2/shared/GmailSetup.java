package edu.duke.ece651.team2.shared;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.Message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * The GmailSetup class sets up the Gmail API for sending emails.
 */
public class GmailSetup {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "ECE651 Team 2 Attendance Manager";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = new ArrayList<>(GmailScopes.all());
    /**
     * Path to the credentials file.
     */
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    /**
     * The Gmail service.
     */
    private final Gmail service;

    /**
     * Creates a new GmailSetup object, in particular initiates the service.
     *
     * @throws IOException              If there is an I/O error.
     * @throws GeneralSecurityException If there is a security error.
     */
    public GmailSetup() throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    // Code from https://developers.google.com/gmail/api/quickstart/java

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = GmailSetup.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        // returns an authorized Credential object.
        return credential;
    }

    /**
     * Sends an email with the specified email address, subject, and body text.
     *
     * @param email    The email address to send the email to.
     * @param subject  The subject of the email.
     * @param bodyText The body text of the email.
     * @throws IOException              If there is an I/O error.
     * @throws GeneralSecurityException If there is a security error.
     */
    public void sendEmail(String email, String subject, String bodyText) throws IOException, GeneralSecurityException {
        String rawEmailString = createRawEmailString(email, "me", subject, bodyText);
        Message message = createMessage(rawEmailString);
        sendMessage(service, "me", message);
    }

    // Code from https://mailtrap.io/blog/java-send-email-gmail/

    /**
     * Creates a base-64 encoded email string with headers.
     *
     * @param to      The email address to send the email to.
     * @param from    The email address to send the email from.
     * @param subject The subject of the email.
     * @param body    The body text of the email.
     * @return The base-64 encoded email string.
     */
    private String createRawEmailString(String to, String from, String subject, String body) {
        String bodyText = "Content-Type: text/plain; charset=\"UTF-8\"\n" +
                "MIME-Version: 1.0\n" +
                "Content-Transfer-Encoding: 7bit\n" +
                "to: " + to + "\n" +
                "from: " + from + "\n" +
                "subject: " + subject + "\n\n" +
                body;

        return Base64.getUrlEncoder().encodeToString(bodyText.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Creates a new Message object with the specified raw email.
     *
     * @param rawEmail The raw email string.
     * @return The Message object.
     */
    private Message createMessage(String rawEmail) {
        Message message = new Message();
        message.setRaw(rawEmail);
        return message;
    }

    /**
     * Sends the specified email.
     *
     * @param service The Gmail service.
     * @param userId  The user ID.
     * @param email   The email to send.
     * @throws IOException If there is an I/O error.
     */
    private void sendMessage(Gmail service, String userId, Message email) throws IOException {
        service.users().messages().send(userId, email).execute();
    }
}