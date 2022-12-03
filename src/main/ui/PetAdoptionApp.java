package ui;

import model.*;
import model.Event;
import persistence.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

// class responsible for the GUI of my project
public class PetAdoptionApp extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 700;
    private static final int BUTTON_WIDTH = 100;
    private static final int BUTTON_HEIGHT = 40;
    private static final int LABEL_WIDTH = 50;
    private static final int LABEL_HEIGHT = 20;
    private static final int TEXT_FIELD_WIDTH = 150;
    private static final int TEXT_FIELD_HEIGHT = 20;
    private final JFrame frame;
    private final JPanel panel;
    private PetsForAdoptionList pets;
    JLabel labelName = new JLabel("Name: ");
    JLabel labelSpecie = new JLabel("Specie: ");
    JLabel labelBreed = new JLabel("Breed: ");
    JLabel labelAge = new JLabel("Age: ");
    JLabel labelNameOfPetWanted = new JLabel("Name of pet you want to adopt: ");
    JLabel labelDisplayPetList = new JLabel();
    JLabel labelDisplayEventLog = new JLabel();
    JTextField textFieldName = new JTextField();
    JTextField textFieldSpecie = new JTextField();
    JTextField textFieldBreed = new JTextField();
    JTextField textFieldAge = new JTextField();
    JTextField textFieldNameOfPetWanted = new JTextField();
    JButton buttonAdopt = new JButton(new AdoptPetAction());
    JButton buttonPost = new JButton(new AddPetAction());
    JButton buttonPrintLog = new JButton(new PrintLogAction());
    JButton buttonHideEventLog = new JButton(new HideEventLogAction());
    private final JMenu menu;
    private final JMenuItem save;
    private final JMenuItem load;
    private final JMenuBar menuBar;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private JLabel labelImage;
    private static final String JSON_STORE = "./data/petsforadoptionlist.json";
    private EventLog el;

    public PetAdoptionApp() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        load = new JMenuItem(new LoadMenuAction());
        save = new JMenuItem(new SaveMenuAction());
        pets = new PetsForAdoptionList();
        panel = new JPanel();
        frame = new JFrame();
        menu.add(save);
        menu.add(load);
        menuBar.add(menu);
        frame.setSize(WIDTH,HEIGHT);
        frame.setTitle("pets adoption");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(null);
        frame.add(panel);
        loadImage();
        setObjectsSize();
        panelAdd();
        frame.setVisible(true);
        setWindowCloseEvent();
    }

    // EFFECTS: print EventLog to console
    private void setWindowCloseEvent() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.getDate());
                    System.out.println(event.getDescription());
                    System.out.println(" ");
                }
            }
        });
    }


    // EFFECTS: helper to assign image to label
    private void loadImage() {
        ImageIcon dogImage = new ImageIcon("dogImage.png");
        labelImage = new JLabel(dogImage);
        labelImage.setVisible(false);
        dogImage.setImage(dogImage.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT));
    }

    // EFFECTS: helper methods, add all the button, label, text field and menu objects to panel
    private void panelAdd() {
        panel.add(labelName);
        panel.add(labelSpecie);
        panel.add(labelBreed);
        panel.add(labelAge);
        panel.add(labelNameOfPetWanted);
        panel.add(labelDisplayPetList);
        panel.add(labelDisplayEventLog);
        panel.add(textFieldName);
        panel.add(textFieldSpecie);
        panel.add(textFieldBreed);
        panel.add(textFieldAge);
        panel.add(textFieldNameOfPetWanted);
        panel.add(buttonAdopt);
        panel.add(buttonPost);
        panel.add(menuBar);
        panel.add(labelImage);
        panel.add(buttonPrintLog);
        panel.add(buttonHideEventLog);
    }

    // EFFECTS: set all the button, label, text field, and menu objects size
    private void setObjectsSize() {
        labelName.setBounds(300, 330, LABEL_WIDTH, LABEL_HEIGHT);
        labelSpecie.setBounds(300, 360, LABEL_WIDTH, LABEL_HEIGHT);
        labelBreed.setBounds(300, 390, LABEL_WIDTH, LABEL_HEIGHT);
        labelAge.setBounds(300, 420, LABEL_WIDTH, LABEL_HEIGHT);
        labelNameOfPetWanted.setBounds(220, 200, 210, LABEL_HEIGHT);
        labelDisplayPetList.setBounds(220,30, 580, 170);
        labelDisplayEventLog.setBounds(550, 240, BUTTON_WIDTH * 2, 300);
        labelImage.setBounds(300, 470,200,200);
        textFieldName.setBounds(350, 330, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textFieldSpecie.setBounds(350, 360, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textFieldBreed.setBounds(350, 390, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textFieldAge.setBounds(350, 420, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textFieldNameOfPetWanted.setBounds(430, 200, TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        buttonAdopt.setBounds(400, 450, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonPost.setBounds(300, 450, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonPrintLog.setBounds(550, 550, BUTTON_WIDTH * 2, BUTTON_HEIGHT);
        buttonHideEventLog.setBounds(550, 550 + BUTTON_HEIGHT, BUTTON_WIDTH * 2, BUTTON_HEIGHT);
        menuBar.setBounds(0,0,WIDTH,20);
    }

    // EFFECTS: print event log once print log button is clicked
    private class PrintLogAction extends AbstractAction {
        PrintLogAction() {
            super("Print log");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            labelDisplayEventLog.setText(setDisplayLog());
        }
    }

    // EFFECTS: hide the event log from the window
    private class HideEventLogAction extends AbstractAction {
        HideEventLogAction() {
            super("Hide event log");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            labelDisplayEventLog.setText(" ");
        }
    }

    // EFFECTS: set the display label to print the event log
    private String setDisplayLog() {
        el = EventLog.getInstance();
        String s = " ";
        for (Event event : el) {
            s = "<html><body>" + s + "<br>" + event.getDate() + "<br>"
                    + event.getDescription() + "<br>" + " " +  "<html><body>";
        }
        return s;

    }

    // EFFECTS: adopt pet with name entered and once adopt pet button is clicked
    private class AdoptPetAction extends AbstractAction {
        AdoptPetAction() {
            super("Adopt pet");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pets.adoptPet(textFieldNameOfPetWanted.getText());
            labelDisplayPetList.setText(setDisplayName());
            labelImage.setVisible(true);
        }
    }

    // EFFECTS: set information that should be displayed
    private String setDisplayName() {
        String displayName = " ";
        for (int i = 0; i < pets.getSize(); i++) {
            displayName = "<html><body>" + displayName + "<br>" + pets.getPet(i).getName()
                    + "(" + pets.getPet(i).getSpecie() + ") " + ", breed:  " + pets.getPet(i).getBreed()
                    + ", age: " + pets.getPet(i).getAge() + "<html><body>";
        }
        return displayName;
    }

    // EFFECTS: add a pet with entered information once add pet button is clicked
    private class AddPetAction extends AbstractAction {
        AddPetAction() {
            super("Post pet");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = textFieldName.getText();
            String specie = textFieldSpecie.getText();
            String breed = textFieldBreed.getText();
            int age = Integer.valueOf(textFieldAge.getText());
            Pet pet = new Pet(name, specie, breed, age);
            pets.addPet(pet);
            labelDisplayPetList.setText(setDisplayName());
            labelImage.setVisible(true);
        }
    }

    // EFFECTS: load saved data when load option from the menu is chosen
    private class LoadMenuAction extends AbstractAction {
        LoadMenuAction() {
            super("load");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadPetsForAdoptionList();
        }
    }

    // EFFECTS: save current data when save option from the menu is chosen
    private class SaveMenuAction extends AbstractAction {
        SaveMenuAction() {
            super("save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            savePetsForAdoptionList();
        }
    }

    // EFFECTS: saves the pets for adoption list to file
    public void savePetsForAdoptionList() {
        try {
            jsonWriter.open();
            jsonWriter.write(pets);
            jsonWriter.close();
            System.out.println("Saved data to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads pets for adoption list from file
    private void loadPetsForAdoptionList() {
        try {
            pets = jsonReader.read();
            System.out.println("Loaded data from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        labelDisplayPetList.setText(setDisplayName());
    }

    public static void main(String[] args) {
        new PetAdoptionApp();
    }
}
