package hr.petsonly.testingPurposeMappings;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/testing")
public class TestingController {

	/*
	 * Zakljucak: ako stavimo @ResponseBody, vraca json ako je objekt, a ne string
	 */
	@RequestMapping(value = "/jsonListTest")
	@ResponseBody
	public List<String> vratiJsonListuTest() {

		List<String> lista = Arrays.asList("prvi", "drugi", "treci", "cetvrti", "peti", "sesti");
		return lista;
	}

	/*
	 * Zakljucak: ovo vrati samo "true", nez kaj sam ocekivao
	 */
	@RequestMapping(value = "/jsonBooleanTest")
	@ResponseBody
	public boolean vratiJsonBooleanTest() {

		return true;
	}
}
