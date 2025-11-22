package com.lms.ui.component;

import com.lms.ui.theme.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ModernButton extends JButton {
    public ModernButton(String text) {
        super(text);
        setFont(Theme.BOLD_FONT);
        setForeground(Color.WHITE);
        setBackground(Theme.ACCENT_COLOR);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Theme.ACCENT_HOVER);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(Theme.ACCENT_COLOR);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

        super.paintComponent(g);
        g2.dispose();
    }
}
