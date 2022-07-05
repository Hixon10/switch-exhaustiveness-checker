package ru.hixon.switchexhaustivenesschecker;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;

final class AnalyzeTaskListener implements TaskListener {
    private final SwitchExhaustiveCheckerProcessor processor;

    public AnalyzeTaskListener(final SwitchExhaustiveCheckerProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void started(TaskEvent taskEvent) {

    }

    @Override
    public void finished(final TaskEvent e) {
        if (e.getKind() != TaskEvent.Kind.ANALYZE) {
            return;
        }

        processor.handleAnalyzedType(e.getTypeElement());
    }
}
