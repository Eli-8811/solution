package com.core.solution.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

	public static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DD_MM_YYYY);
		return dateFormat.format(date);
	}

	@SuppressWarnings("unused")
	private static ByteArrayInputStream cloneFileBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int chunk = 0;
		byte[] data = new byte[4096];
		while (-1 != (chunk = inputStream.read(data))) {
			baos.write(data, 0, chunk);
		}
		baos.flush();
		baos.close();
		byte[] bytes = baos.toByteArray();
		return new ByteArrayInputStream(bytes);
	}
	
}

