package com.leninkumar.amazonlocker.utils;

import com.leninkumar.amazonlocker.exceptions.PackageSizeMappingException;
import com.leninkumar.amazonlocker.models.LockerSize;

public class Utils {
	public static LockerSize getLockerSizeFromPackageSize(double packageSize) throws PackageSizeMappingException {
		if (packageSize < 2.0) {
			return LockerSize.XS;
		}
		if (packageSize < 4.0) {
			return LockerSize.S;
		}
		if (packageSize < 6.0) {
			return LockerSize.M;
		}
		if (packageSize < 8.0) {
			return LockerSize.L;
		}
		if (packageSize < 10.0) {
			return LockerSize.XL;
		}
		throw new PackageSizeMappingException("Couldn't find the package size");
	}

	public static String generateRandomCode(int length) {
		char[] code = new char[length];
		for (int i = 0; i < length; i++) {
			code[i] = (char) ('0' + (int) (Math.random() * 10));
		}
		return new String(code);
	}
}
