package com.zzm;

import com.zzm.dataBase.dbManager;
import com.zzm.appGUI.launcherGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        if(dbManager.getConnection() != null) {
            System.out.println("MySQL Connected");
        } else {
            System.out.println("Failure");
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new launcherGUI().getjFrame();
            }
        });
    }
}