package jrcengine.Manage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import jrcengine.Interface.IFace_FileIO;


public class Manage_Settings {

    public final static String file = ".ppsave";
    
    public static final int GAME_WIDTH = 320;
    public static final int GAME_HEIGHT = 480;
    
    public static void load(IFace_FileIO files) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(files.readFile(file)));
        
        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e) {
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }
    
    public static void save(IFace_FileIO iFace_FileIO) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    iFace_FileIO.writeFile(file)));

        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }
	
}
