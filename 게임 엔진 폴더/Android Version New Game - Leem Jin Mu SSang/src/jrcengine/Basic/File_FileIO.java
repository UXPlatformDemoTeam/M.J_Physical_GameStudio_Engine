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
	protected AssetManager assets;
	protected String externalStoragePath;

	public File_FileIO(AssetManager assets) {
		this.assets = assets;
		this.externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
	}

	@Override
	public InputStream readAsset(String fileName) throws IOException {
		return assets.open(fileName);
	}

	@Override
	public InputStream readFile(String fileName) throws IOException {
		return new FileInputStream(externalStoragePath + fileName);
	}

	@Override
	public OutputStream writeFile(String fileName) throws IOException {
		return new FileOutputStream(externalStoragePath + fileName);
	}

}
