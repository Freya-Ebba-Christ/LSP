package com.example.lsp4;

import org.eclipse.lsp4j.*;
import org.eclipse.lsp4j.services.TextDocumentService;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import java.util.ArrayList;
import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionList;
import org.eclipse.lsp4j.jsonrpc.messages.Either;

public class MyDslTextDocumentService implements TextDocumentService {

    @Override
    public CompletableFuture<Hover> hover(HoverParams params) {
        // Return a basic hover response
        MarkupContent content = new MarkupContent();
        content.setKind(MarkupKind.MARKDOWN);
        content.setValue("Hover information for: " + params.getTextDocument().getUri());
        return CompletableFuture.completedFuture(new Hover(content));
    }

    @Override
    public CompletableFuture<Either<List<CompletionItem>, CompletionList>> completion(CompletionParams params) {
        // Simple autocompletion example
        List<CompletionItem> items = new ArrayList<>();
        CompletionItem item = new CompletionItem("SampleCompletion");
        items.add(item);
        return CompletableFuture.completedFuture(Either.forLeft(items));
    }

    @Override
    public CompletableFuture<List<Either<SymbolInformation, DocumentSymbol>>> documentSymbol(DocumentSymbolParams params) {
        // Example of symbol information for the document
        List<Either<SymbolInformation, DocumentSymbol>> symbols = new ArrayList<>();
        SymbolInformation symbolInfo = new SymbolInformation();
        symbolInfo.setName("SampleSymbol");
        symbols.add(Either.forLeft(symbolInfo));
        return CompletableFuture.completedFuture(symbols);
    }

    @Override
    public CompletableFuture<List<? extends TextEdit>> formatting(DocumentFormattingParams params) {
        // Example of formatting
        List<TextEdit> edits = new ArrayList<>();
        TextEdit edit = new TextEdit();
        edit.setNewText("Formatted text");
        edits.add(edit);
        return CompletableFuture.completedFuture(edits);
    }

    @Override
    public void didOpen(DidOpenTextDocumentParams params) {
        // Track or log the document open event for future use
        System.out.println("Document opened: " + params.getTextDocument().getUri());
    }

    @Override
    public void didClose(DidCloseTextDocumentParams params) {
        // Track or log the document close event for future use
        System.out.println("Document closed: " + params.getTextDocument().getUri());
    }

    @Override
    public void didChange(DidChangeTextDocumentParams params) {
        // Track or log the document change event for future use
        System.out.println("Document changed: " + params.getTextDocument().getUri());
    }

    @Override
    public void didSave(DidSaveTextDocumentParams params) {
        // Track or log the document save event for future use
        System.out.println("Document saved: " + params.getTextDocument().getUri());
    }

}
