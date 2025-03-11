package com.example.lsp4;

import java.util.ArrayList;
import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.WorkspaceService;
import java.util.concurrent.CompletableFuture;
import java.util.List;

public class MyDslWorkspaceService implements WorkspaceService {

    public CompletableFuture<List<? extends Location>> gotoDefinition(DefinitionParams params) {
        // Provide the locations for "go to definition"
        List<Location> locations = new ArrayList<>();
        Location location = new Location();
        location.setUri(params.getTextDocument().getUri());
        locations.add(location);
        return CompletableFuture.completedFuture(locations);
    }

    @Override
    public CompletableFuture<Object> executeCommand(ExecuteCommandParams params) {
        // Execute your command (simplified example)
        String command = params.getCommand();
        System.out.println("Executing command: " + command);
        return CompletableFuture.completedFuture(null); // If no result is needed
    }

    @Override
    public void didChangeWatchedFiles(DidChangeWatchedFilesParams params) {
        // Handle changes in watched files (simplified)
        System.out.println("Files changed: " + params.getChanges());
    }

    @Override
    public void didChangeConfiguration(DidChangeConfigurationParams params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void didChangeWorkspaceFolders(DidChangeWorkspaceFoldersParams params) {
        WorkspaceService.super.didChangeWorkspaceFolders(params); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
