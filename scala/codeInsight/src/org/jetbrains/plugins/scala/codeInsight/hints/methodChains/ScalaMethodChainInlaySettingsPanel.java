package org.jetbrains.plugins.scala.codeInsight.hints.methodChains;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ScalaMethodChainInlaySettingsPanel {
    private JPanel panel;
    private JSpinner typeCountSpinner;
    private final SpinnerNumberModel typeCountModel;
    private final Supplier<Integer> typeCountGetter;

    public ScalaMethodChainInlaySettingsPanel(Supplier<Integer> typeCountGetter, Consumer<Integer> typeCountSetter) {
        this.typeCountGetter = typeCountGetter;
        typeCountModel = new SpinnerNumberModel();
        $$$setupUI$$$();
        typeCountModel.setMinimum(1);
        typeCountModel.setMaximum(500);
        typeCountModel.setStepSize(1);
        reset();
        typeCountModel.addChangeListener((e) -> typeCountSetter.accept(typeCountModel.getNumber().intValue()));
    }

    public JPanel getPanel() {
        return panel;
    }

    public void reset() {
        typeCountModel.setValue(typeCountGetter.get());
    }

    private void createUIComponents() {
        typeCountSpinner = new JSpinner(typeCountModel);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel = new JPanel();
        panel.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Minimal unique type count to show method chains");
        panel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel.add(typeCountSpinner, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel.add(spacer2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        label1.setLabelFor(typeCountSpinner);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}