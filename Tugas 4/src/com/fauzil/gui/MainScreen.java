package com.fauzil.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JList jListMahsiswa;
    private JButton buttonFilter;
    private JTextField textFieldFilter;
    private JTextField textFieldNim;
    private JTextField textFieldNama;
    private JTextField textFieldipk;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonClear;
    private List<Mahasiswa> ArrayLIstMahasiswa = new ArrayList<>();
    private DefaultListModel defaultListModel = new DefaultListModel();

    class Mahasiswa {
        private String nim;
        private String nama;
        private float ipk;

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public float getIpk() {
            return ipk;
        }

        public void setIpk(float ipk) {
            this.ipk = ipk;
        }
    }


    public MainScreen() {
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = textFieldNim.getText();
                String nama = textFieldNama.getText();
                float ipk = Float.parseFloat(textFieldipk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setIpk(ipk);
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);

                ArrayLIstMahasiswa.add(mahasiswa);
                clearForm();

                FromListMahasiswaToListModel();

            }
        });
        jListMahsiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListMahsiswa.getSelectedIndex();

                if(index < 0)
                    return;

                String nama = jListMahsiswa.getSelectedValue().toString();

                for (int i = 0; i <ArrayLIstMahasiswa.size(); i++) {
                    if (ArrayLIstMahasiswa.get(i).getNama().equals(nama)) {
                        Mahasiswa mahasiswa = ArrayLIstMahasiswa.get(i);
                        textFieldipk.setText(String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText(mahasiswa.getNama());
                        textFieldNim.setText(mahasiswa.getNim());
                        break;
                    }
                }
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListMahsiswa.getSelectedIndex();

                if(index < 0)
                    return;

                String nama = jListMahsiswa.getSelectedValue().toString();

                for(int i = 0; i < ArrayLIstMahasiswa.size();i++) {
                    if (ArrayLIstMahasiswa.get(i).getNama().equals(nama) ) {
                        ArrayLIstMahasiswa.remove(i);
                        break;
                    }
                }

                clearForm();
                FromListMahasiswaToListModel();
            }
        });
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyWord = textFieldFilter.getText();

                List<String> filtered = new ArrayList<>();

                for (int i = 0; i<ArrayLIstMahasiswa.size(); i++) {
                    if(ArrayLIstMahasiswa.get(i).getNama().contains(keyWord)) {
                        filtered.add(ArrayLIstMahasiswa.get(i).getNama());
                    }
                }

                refreshListModel(filtered);
            }
        });
    }

    private void FromListMahasiswaToListModel() {
        List<String> listnamaMahasiswa = new ArrayList<>();

        for(int i = 0; i< ArrayLIstMahasiswa.size(); i++){
            listnamaMahasiswa.add(
                    ArrayLIstMahasiswa.get(i).getNama()
            );
        }

        refreshListModel(listnamaMahasiswa);
    }


    private void clearForm(){
        textFieldipk.setText("");
        textFieldNim.setText("");
        textFieldNama.setText("");
    }
    private void refreshListModel(List<String> List) {
        defaultListModel.clear();
        defaultListModel.addAll(List);
        jListMahsiswa.setModel(defaultListModel);

    }

    public static void main(String [] args ) {
            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);

        }
    }
