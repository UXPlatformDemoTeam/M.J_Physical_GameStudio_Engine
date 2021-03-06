package jrcengine.Basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jrcengine.Interface.IFace_FileIO;
import android.content.res.AssetManager;
import android.os.Environment;

public class File_FileIO implements IFace_FileIO {
	AssetManager assets;
	String externalStoragePath;
	private int int_mapping_Scale_X;
	private int int_mapping_Scale_Y;

	public File_FileIO(AssetManager assets, int int_mappingX, int int_mappingY) {
		this.assets = assets;
		this.externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;

	}

	public File_FileIO(AssetManager assets) {
		this.assets = assets;
		this.externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
	}

	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}

	public InputStream readFile(String fileName) throws IOException {
		return new FileInputStream(externalStoragePath + fileName);
	}

	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(externalStoragePath + fileName);
	}

	public int getMappingScaleX() {
		return int_mapping_Scale_X;
	}

	public int getMappingScaleY() {
		return int_mapping_Scale_Y;
	}

}
