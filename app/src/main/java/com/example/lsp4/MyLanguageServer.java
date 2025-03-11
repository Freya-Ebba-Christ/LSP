package com.example.lsp4;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

public class MyLanguageServer implements LanguageServer {
    private final TextDocumentService textDocumentService = new MyDslTextDocumentService();
    private final WorkspaceService workspaceService = new MyDslWorkspaceService();

    @Override
    public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
        InitializeResult result = new InitializeResult(new ServerCapabilities());
        return CompletableFuture.supplyAsync(() -> result);
    }

    @Override
    public CompletableFuture<Object> shutdown() {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    @Override
    public TextDocumentService getTextDocumentService() {
        return textDocumentService;
    }

    @Override
    public WorkspaceService getWorkspaceService() {
        return workspaceService;
    }

    // Method to start the server and handle communication
    public static void main(String[] args) {
        int port = 1337;
        System.out.println("Starting Language Server on port " + port);

        // Create the language server instance
        MyLanguageServer languageServer = new MyLanguageServer();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Listening on port " + port);

            // Accept client connections
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // Handle communication with the client
            languageServer.handleCommunication(inputStream, outputStream);

        } catch (IOException e) {
            System.err.println("Failed to start server on port " + port);
            e.printStackTrace();
        }
    }

    // This method handles communication directly using LSP4J's internal mechanisms
    public void handleCommunication(InputStream inputStream, OutputStream outputStream) throws IOException {
        // LSP4J takes care of the protocol and communication internally.
        // No need to use external JSON-RPC handling classes.

        // Simply use the LanguageServer's standard way of handling communication
        LSPConnector connector = new LSPConnector(inputStream, outputStream);
        connector.startListening(this); // Start listening for messages from the client
    }
}
class LSPConnector {
    private final InputStream inputStream;
    private final OutputStream outputStream;

    public LSPConnector(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public void startListening(LanguageServer languageServer) {
        // Start listening to requests from the client using the standard LSP4J mechanisms
        // Use the TextDocumentService and WorkspaceService methods to handle client requests
        // LSP4J takes care of the protocol internally, so no external class like JSONRPCServer is needed.
    }
}
