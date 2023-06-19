package com.iSully.TagGame;

import com.google.gson.Gson;
import com.google.inject.Inject;
import io.socket.client.Socket;
import net.runelite.api.Client;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TagGamePluginPanel extends PluginPanel implements ActionListener {
    private TagGamePlugin plugin;

    private Socket socket;

    private ConnectionManager connectionManager;

    private JButton myButton;
    private JComboBox<String> comboBox;
    private JLabel title;

    private Client client;

    public String partyCode;

    public TagGamePluginPanel(TagGamePlugin plugin, Client client) {
        this.socket = plugin.socket;
        this.client = client;
        this.plugin = plugin;
        this.connectionManager = new ConnectionManager(client, new Gson());

        // Set layout to GridBagLayout
        setLayout(new GridBagLayout());

        // Create GridBagConstraints object to set component constraints
        GridBagConstraints gbc = new GridBagConstraints();

        // Set title constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0); // add padding
        title = new JLabel("Tag!");
        title.setFont(FontManager.getRunescapeBoldFont());
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, gbc);

        // Set label and button constraints for third row
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, 5, 0); // add padding
        JLabel label2 = new JLabel("Custom Model List:");
        add(label2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 0, 5, 0); // add padding
        comboBox = new JComboBox<>();
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
//                if(PropHuntModelId.map.get(selectedOption) == null) return;
//
//                plugin.set(PropHuntModelId.valueOf(selectedOption));
            }
        });
        updateComboBox();
        add(comboBox, gbc);

        // Set label and button constraints for second row
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 3); // add padding
        myButton = new JButton("Create Party");
        myButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connectionManager.createNewGame(client.getLocalPlayer());
            }
        });
        add(myButton, gbc);

        // Set label and button constraints for second row
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 0, 5, 3); // add padding
        myButton = new JButton("Join Game");
        myButton.addActionListener(this);
        add(myButton, gbc);

        // Set label and button constraints for second row
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, 5, 3); // add padding
        JButton rotateClockwiseButton = new JButton("Leave Game");
        rotateClockwiseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                plugin.rotateModel(1);
            }
        });
        add(rotateClockwiseButton, gbc);

        // Set label and button constraints for second row
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 3, 5, 0); // add padding
        JButton rotateCounterButton = new JButton("Start Game");
        rotateCounterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                plugin.rotateModel(-1);
            }
        });
        add(rotateCounterButton, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        plugin.setRandomModelID();
    }

    public void updateComboBox() {
        comboBox.removeAllItems();
//        PropHuntModelId.map.keySet().forEach(item -> comboBox.addItem(item));
    }

    public String getGameStatus() {
        return "Status";
    }


}
