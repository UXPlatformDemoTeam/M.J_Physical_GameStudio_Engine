package jrcengine.Manage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import jrcengine.Interface.IFace_FileIO;
import pangpang.MainGameLeemJinMuSSang.SettingWindow;
import pangpang.MainGameLeemJinMuSSang.StageInformation;
import pangpang.MainGameLeemJinMuSSang.StageWindow;
import android.util.Log;

public class Manage_Settings {

	public static int nUnitMonkManLv = 1;
	public static int nUnitLeesunsinLv = 1;
	public static int nUnitAxeManLv = 1;
	public static int nUnitArcherLv = 1;
	public static int nUnitSpearLv = 1;
	public static int nStageCoin = 0;

	public static int nStageLv01 = 0;
	public static int nStageLv02 = 0;
	public static int nStageLv03 = 0;

	public static float fSoundVolume = 0f;
	public static float fMusicVolume = 0f;

	public static int nBackGroundSoundVolume = 0;
	public static int nEffectSoundVolume = 0;

	public static boolean isSoundMusicVolume = true;
	public static boolean isVibration = true;

	public final static String file = ".mussang";

	public static void load(IFace_FileIO files) {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(files.readFile(file)));
			Manage_Settings.fSoundVolume = Float.parseFloat(in.readLine());
			Manage_Settings.fMusicVolume = Float.parseFloat(in.readLine());
			SettingWindow.isLocation = Boolean.parseBoolean(in.readLine());
			SettingWindow.isVibration = Boolean.parseBoolean(in.readLine());
			Manage_Settings.nUnitMonkManLv = Integer.parseInt(in.readLine());
			Manage_Settings.nUnitAxeManLv = Integer.parseInt(in.readLine());
			Manage_Settings.nUnitLeesunsinLv = Integer.parseInt(in.readLine());
			Manage_Settings.nStageCoin = Integer.parseInt(in.readLine());
			StageInformation.Stage.s1.nStageStar = Integer.parseInt(in
					.readLine());
			StageInformation.Stage.s2.nStageStar = Integer.parseInt(in
					.readLine());
			StageInformation.Stage.s3.nStageStar = Integer.parseInt(in
					.readLine());
			StageInformation.Stage.s4.nStageStar = Integer.parseInt(in
					.readLine());
			StageInformation.Stage.s5.nStageStar = Integer.parseInt(in
					.readLine());
			StageInformation.Stage.s6.nStageStar = Integer.parseInt(in
					.readLine());
			StageInformation.Stage.s7.nStageStar = Integer.parseInt(in
					.readLine());
			StageInformation.Stage.s8.nStageStar = Integer.parseInt(in
					.readLine());
			StageWindow.SELECTSTABEMAXNUM = Integer.parseInt(in.readLine());
			Manage_Settings.nBackGroundSoundVolume = Integer.parseInt(in
					.readLine());
			Manage_Settings.nEffectSoundVolume = Integer
					.parseInt(in.readLine());
			
			Log.e("test : " + Manage_Settings.nUnitMonkManLv + " "
					+ Manage_Settings.nUnitAxeManLv + " "
					+ StageInformation.Stage.s1.nStageStar + " "
					+ StageInformation.Stage.s2.nStageStar + " "
					+ StageInformation.Stage.s3.nStageStar + " "
					+ StageInformation.Stage.s4.nStageStar + " "
					+ StageInformation.Stage.s5.nStageStar + " "
					+ StageInformation.Stage.s6.nStageStar + " "
					+ StageInformation.Stage.s7.nStageStar + " "
					+ StageInformation.Stage.s8.nStageStar, "test");
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
			out.write(Float.toString(Manage_Settings.fSoundVolume));
			out.write("\n");
			out.write(Float.toString(Manage_Settings.fMusicVolume));
			out.write("\n");
			out.write(Boolean.toString(SettingWindow.isLocation));
			out.write("\n");
			out.write(Boolean.toString(SettingWindow.isVibration));
			out.write("\n");
			out.write(Integer.toString(Manage_Settings.nUnitMonkManLv));
			out.write("\n");
			out.write(Integer.toString(Manage_Settings.nUnitAxeManLv));
			out.write("\n");
			out.write(Integer.toString(Manage_Settings.nUnitLeesunsinLv));
			out.write("\n");
			out.write(Integer.toString(Manage_Settings.nStageCoin));
			out.write("\n");
			out.write(Integer.toString(StageInformation.Stage.s1.nStageStar));
			out.write("\n");
			out.write(Integer.toString(StageInformation.Stage.s2.nStageStar));
			out.write("\n");
			out.write(Integer.toString(StageInformation.Stage.s3.nStageStar));
			out.write("\n");
			out.write(Integer.toString(StageInformation.Stage.s4.nStageStar));
			out.write("\n");
			out.write(Integer.toString(StageInformation.Stage.s5.nStageStar));
			out.write("\n");
			out.write(Integer.toString(StageInformation.Stage.s6.nStageStar));
			out.write("\n");
			out.write(Integer.toString(StageInformation.Stage.s7.nStageStar));
			out.write("\n");
			out.write(Integer.toString(StageInformation.Stage.s8.nStageStar));
			out.write("\n");
			out.write(Integer.toString(StageWindow.SELECTSTABEMAXNUM));
			out.write("\n");
			out.write(Integer.toString(Manage_Settings.nBackGroundSoundVolume));
			out.write("\n");
			out.write(Integer.toString(Manage_Settings.nEffectSoundVolume));
			out.write("\n");
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
