package util;



class PeriodoUtilsTest extends GroovyTestCase {
	
	
	def testInicioDeMes(){
		def date=Date.parse('dd/MM/yyyy','25/12/2012')
		assertEquals(Date.parse('dd/MM/yyyy','01/12/2012'), date.inicioDeMes())
	}

}
