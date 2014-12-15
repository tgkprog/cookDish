package academic.cookSalad;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.s2n.ddt.util.DdtIoUtls;
import org.s2n.ddt.util.ExcelAccess;

/**
 * Can use DdtUtils excel reader (which uses POI) to read an excel and run data cases
 * More pain to make but once made can add a variety of test cases
 * and validations per truth table at 
 * 
 * TODO 
 * @author tushar
 *
 */
public class CookDataTest {
	
	final static String SHT_NAME = "Sheet1";

	@Test
	public void data(){
		ExcelAccess xls = initExcelData();
		/*
		 cook = new Cook();
		in = new Ingredients();
		in.setBeans(100);
		in.setIceBergLettuce(100);
		in.setChickens(100);
		in.setCarrots(100);
		Status st = cook.cookIt(in, Dish.CEASERS);
		assertTrue("Enough ingredients ceasrers ", st.isSuccess());
		assertEquals(1, 1);

		 */
	
		Cook cook = new Cook();
		Ingredients in = new Ingredients();
		Dish dish = null;
		int r = 11;
		int c = 0;
		String typ = xls.getCellValue(SHT_NAME, r, c);
		int cases = 0;
		int pass = 0;
		while(typ != null && typ.length() > 0){
			cases++;
			typ = typ.toLowerCase();
			if(typ.equals("ceaser")){
				dish = Dish.CEASERS;
			}else{
				dish = Dish.RUSSIAN;
			}
			in.setCarrots(getInt(xls, r, 1));
			in.setChickens(getInt(xls, r, 2));
			in.setBeans(getInt(xls, r, 3));
			in.setIceBergLettuce(getInt(xls, r, 4));
			Status sta = cook.cookIt(in, dish);
			
			Status exp = parseExpected(xls, r);
			Ingredients out = exp.getLeftOverIngrediants();
			if(sta.isSuccess() == exp.isSuccess()){
				
				if(in.equalValues(out)){
					pass++;
				}else{
					CookTest.print("Expected", exp);
					CookTest.print("Actual Status okay but left over ", sta);
				}
			}else{
				CookTest.print("Expected", exp);
				CookTest.print("Actual", sta);
			}
			r++;
			typ = xls.getCellValue(SHT_NAME, r, c);
		}
		System.err.println("Done " + cases + " pass " + pass);
		assertEquals("Total cases vs pass ", cases, pass);
	}

	private Status parseExpected(ExcelAccess xls, int r) {
		Status exp = new Status();
		Ingredients out = new Ingredients();
		out.setCarrots(getInt(xls, r, 6));
		out.setChickens(getInt(xls, r, 7));
		out.setBeans(getInt(xls, r, 8));
		out.setIceBergLettuce(getInt(xls, r, 9));
		exp.setLeftOverIngrediants(out);
		String sxp = xls.getCellValue(SHT_NAME, r, 10);
		boolean expb = "true".equals(sxp);
		exp.setSuccess(expb);
		return exp;
	}
	
	private int getInt(ExcelAccess x, int r, int c){
		String d = x.getCellValue(SHT_NAME, r, c);
		if(d != null && d.length() > 0){
			return Integer.parseInt(d);
		}
		return -1;
	}
	
	private ExcelAccess initExcelData(){
		DdtIoUtls.printCurrentFolderName();
		ExcelAccess xlsWk = new ExcelAccess();
		// /u/w/w4moh/CookSalad/src/test/resources/TruthTableCookIt.xlsx
		final String full = "/u/w/w4moh/CookSalad/src/test/resources/TruthTableCookIt.xlsx";
		final String rel = "./src/test/resources/TruthTableCookIt.xlsx";
		xlsWk.openWorkBook(new java.io.File(full));
		String ss = xlsWk.getCellValue("Sheet1", 10, 3);
		System.out.println(":" + ss + ". 2 :" + xlsWk.getCellValue("Sheet1", 9, 3) + ".");
		return xlsWk;
		
	}
}
