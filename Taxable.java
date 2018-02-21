/* Taxable interface */


import java.math.*;
import java.text.*;
import java.util.*;
import java.io.*;
interface Taxable  {
	
	void calculateTax(double taxRate);

    double getTaxAmount();

	String createTaxStatement();
	
}
