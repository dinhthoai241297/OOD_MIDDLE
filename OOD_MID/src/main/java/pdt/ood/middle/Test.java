package pdt.ood.middle;

import org.apache.commons.lang3.StringUtils;

public class Test {
	public static void main(String[] args) {
		String text1 = "123a";
		String text2 = "123";
		System.out.println(StringUtils.isNumeric(text1));
		System.out.println(StringUtils.isNumeric(text2));
	}
}
