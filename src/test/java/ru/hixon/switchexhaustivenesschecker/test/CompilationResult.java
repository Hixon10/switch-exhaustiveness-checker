package ru.hixon.switchexhaustivenesschecker.test;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.util.List;

class CompilationResult {
    private final boolean ok;
    private final List<Diagnostic<? extends JavaFileObject>> diagnostics;

    CompilationResult(boolean ok, List<Diagnostic<? extends JavaFileObject>> diagnostics) {
        this.ok = ok;
        this.diagnostics = diagnostics;
    }

    public boolean isOk() {
        return ok;
    }

    public List<Diagnostic<? extends JavaFileObject>> getDiagnostics() {
        return diagnostics;
    }

    public String getDiagnosticsAsString() {
        if (diagnostics.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
            sb.append(diagnostic.toString());
            sb.append(" ");
        }
        return sb.toString();
    }
}
