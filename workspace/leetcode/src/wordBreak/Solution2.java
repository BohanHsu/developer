package wordBreak;

import java.util.HashSet;
import java.util.Set;

public class Solution2 {
	public boolean wordBreak(String s, Set<String> dict) {
		HashSet<Integer> length = new HashSet<Integer>();
		int min = Integer.MAX_VALUE; // the minimum length in dict
		int max = Integer.MIN_VALUE; // the maximum length in dict
		int len = 0;

		// set the min and max
		for (String str : dict) {
			len = str.length();
			length.add(len);
			if (len < min) {
				// set the min
				min = len;
			}
			if (len > max) {
				// set the max
				max = len;
			}
		}

		return wordBreak(s, dict, min, max, length);
	}

	private boolean wordBreak(String s, Set<String> dict, int min, int max,
			HashSet<Integer> distLength) {

		boolean result = false;
		String word = null;
		String remain = null;
		int len = s.length();

		if (len < min) {
			return false;
		}

		max = max < len ? max : len;

		for (Integer i : distLength) {
			if (i <= max) {
				word = s.substring(0, i);
				remain = s.substring(i, len);

//				System.out.println("word:" + word);
//				System.out.println("remain:" + remain);
				
				if ("".equals(remain)) {
					result = dict.contains(word);
				} else {
					result = dict.contains(word)
							&& wordBreak(remain, dict, min, max, distLength);
				}
				if (result == true) {
					return result;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Solution2 s = new Solution2();
		String[] d = new String[] { "be", "ellaekgjhibcomc", "ahaklkan", "jcm",
				"lchidklmcone", "ljmdgagaen", "giioojldjkfnno", "el",
				"eibjaffacjll", "hn", "hbjakhjneml", "foi", "nbhf", "aigf",
				"cfdlnc", "fa", "amakgofedhkghgl", "ddhmhdhioh",
				"ijoddeabbiei", "giamcgco", "nholghlfbbendi", "emlc", "m",
				"elgibme", "behkignjenmh", "lodkkjgioe", "doe", "hgflgakna",
				"macghogdidmdm", "ec", "kncigolkog", "ljooio", "lch",
				"gkaclkbjn", "ofiaglfoffl", "alhfbb", "cfmdbgo", "cfcnajknk",
				"jfh", "almbgdjnbbbgmhb", "dmlnnohf", "olojeejfafc",
				"ndgcmgoe", "cmkdjilfeo", "bengdd", "enfg", "dbngiggni",
				"anmkljn", "njdnjdmmfha", "ndimmddfmhe", "hmkdjkhhiilnf",
				"ehd", "jfdl", "dlgki", "bhoflnomibkki", "lg",
				"fjojjnnkdfenhol", "lefhhl", "nimdl", "gejgomblmim",
				"ahbnlmlfmejjj", "glhacaojnf", "mfjdhnhdkm", "do", "fnh",
				"mnmjdk", "hfjgdlnnb", "hniolfhkhbie", "ldgodonogcab",
				"mabjnnohnijhn", "aceojlkmdg", "aedfljg", "cehk", "jag",
				"oniegflnhje", "jo", "maokc", "jkbndc", "djbn", "dajkdblojkf",
				"dmen", "kcdjdocinenc", "cgindbm", "h", "odaof", "cmogcbgj",
				"anjahlgbbmba", "haoe", "ggacnminj", "ilcfjoedhe",
				"klookammgofl", "onnmenn", "mbdneaioo", "jc", "dekgil",
				"bjdfibfd", "hfbnlgmlllcb", "afebehf", "obekljbnh", "eoaedhjk",
				"nobamccd", "mdieojoknf", "komcglmakkaa", "jcliimcc",
				"jmmgbmha", "gdogjnn", "ednembco", "dgno", "jiaheeabifahfmo",
				"djkcgnakkh", "kdkiglgf", "eb", "fmdnlhj", "eicohdciejc",
				"jgofmankkf", "o", "nnmomkmkmiaoga", "njchkccln", "ndamha",
				"eleanmojdi", "ebkl", "jcageehlelcg", "acfddofjihgmn",
				"iklaomfhjm", "io", "igmob", "lfnhnlnigbbignk", "anihfojmedf",
				"nj", "oilcabalhb", "adjfbkfjch", "lbfb", "mgfnngfccb",
				"jhmhggm", "dnllc", "c", "ljim", "jmikd", "mdfimdgac",
				"fhbclgo", "edclcdia", "nelmfjejff", "i", "cmcbbckdnjcoo",
				"cddocce", "hc", "keh", "keofhnhemd", "biln", "mjcnbjmkikon",
				"fekbdnkolahh", "hkfmjbj", "mjoj", "jn", "ilof", "ifhfk",
				"aofmg", "nofljgmmmf", "hcdifeiclbchlf", "imlijgdg",
				"ocdiiemcmbkglm", "nhoekmlkjfoa", "kibffkbleedda",
				"kdhdjekccbkc", "bcbflcag", "jekmkdimnnjjoo", "mmgfljchalbem",
				"kchk", "oi", "ncf", "jembgfa", "l", "kfkeianmmmdacl",
				"ecjkkfggj", "jdgcfnhfjonkhig", "jhagiokii", "nifm", "bbjjlj",
				"adajlokomibfg", "ojk", "lockdel", "bh", "hoojolglchck",
				"conko", "eadi", "kfigoijnfimolen", "g", "dbnj", "cojkbmo",
				"hh", "mcdbh", "ngdmgioen", "ehjagfohnolkho", "dgfgdlc",
				"aoglneoh", "gbc", "ijjckddeicld", "imekih", "liiaecniil",
				"hahejbhgiclb", "fnmojm", "ablihjhggiahhno", "colloaakco",
				"jhobddaanbhmlg", "cbfajfhkoh", "cim", "laghknigabn",
				"dcbnbkegkjam", "gem", "ljjim", "icclogji", "omidhe", "f",
				"giiaclfcjkagl", "ndcjldekjnkekm", "aiikdccohcj", "mkbmb",
				"oomhhafobic", "bkacdjfgbggn", "ahghdoahbi", "hedm", "eeoj",
				"bdgdlfollegej", "eg", "dfeb", "dkffkid", "hcne",
				"gjkohnaaabn", "jfeododjgdhlfbf", "clfkmconnkfb", "abnbkcni",
				"hk", "ghnmhjm", "oibjibmkaibdefa", "hjambim", "oe", "aao",
				"jil", "fmhomflfen", "hlidcklnmb", "hiaonkhd", "bibbmkandf",
				"hke", "bmfcionm", "inhcnlkbkkmjicn", "jckjedhgoghi", "chmik",
				"mnjldknhaec", "hocbccbg", "ljadj", "lciikgnlj",
				"ifjjhkbhifione", "foikblanoco", "ode", "mjc", "fhklofh",
				"mmoklkkog", "hocbojmhffeajo", "ccmmd", "bkkh",
				"nhhgcflniebkme", "lfohikenfbjacli", "cmehijnihijgng", "caa",
				"bmk", "emofof", "jjagiogohfab", "ibh", "eoacdlnodalkjbl",
				"cbbjbbnjom", "iljiomeloehen", "gignlngclmh", "b", "ll",
				"dokgngnocde", "cienegffibgieba", "agbachloidg", "mlelnafokd",
				"nmcmka", "akeogjbjcnf", "nebdic", "a", "efc", "ljdk", "jhcag",
				"bkbikbjgae", "mcjlgjeo", "lo", "dbiofobl", "cehebiljff",
				"eeagngm", "ondahcjiel", "coblanndhlhoggj", "jaobmjml",
				"jfejjinofek", "hhnna", "gdhcn", "acelcomgkgohm", "njkkjkkln",
				"jmc", "hkekoho", "boefec", "cioibfgjmhb", "ebggdbeimn",
				"emhg", "cfghkhii", "d", "k", "khoddedia", "nhje", "eebkfml",
				"bohhd", "kg", "n", "ilgemokdehcbaif", "cldicda", "einij",
				"akmabcgfn", "fmkmcn", "bnlfbagkke", "oakbgjejmcncj",
				"iehdfadgoik", "kkcfo", "jmjkmfcacjjnd", "ndokhh",
				"hjfeelhckkjjmj", "dnomohejbodkb", "jcmblncjadno",
				"oiofcodobiml", "ddmillkncjfdfj", "aihenmkdnhdhkhf",
				"bfdbakeilfdojnc", "jjhbkbne", "aigabk", "cae", "oednojjb",
				"gdoe", "jokjceohkmbm", "offkanbahigo", "kfomigbfddjli",
				"dkkjobgkcejei", "mdilld", "bofkika", "kkinig",
				"cljcflbghjmhmke", "kmbjlgdcbdjn", "bkgbmoahda",
				"kmnajjdemggnfg", "mgjndldil", "iemb", "kehaokgjg", "icign",
				"oijmaolehmoo", "amhgldifmgekhe", "diacnollhi", "lnjhdaafadl",
				"bdfiackhogoje", "ebjlfa", "deabkgfhnead", "gadcob", "haa",
				"dbhnbhjcmmab", "bbmjainilbbej", "dc", "bgjgafnjjflne",
				"ehholgnn", "fmhccbnc", "mdnfl", "feeejdgc", "mfhlobdadooh",
				"ojna", "gkgjnijdbgo", "ghngnhn", "nhnjaiaadiedgg", "nk",
				"hmd", "nmbmijaffogl", "onkcgbgmago", "gfli", "ofjlec",
				"nlfnbkkdc", "hakilani", "bofjdjkhllb", "ocjncleljnecfc",
				"gdonnkodmkejhf", "fiflchanfllgnf", "kaejakoibgln",
				"hmdlfioacgaci", "honmfbcog", "mlacbi", "gf", "ejbbemoeha",
				"acfjegee", "lllflaocnnkeadi", "mdgoebfgacecmbg", "faejgln",
				"kmlmhffgcmekm", "akcjmgdg", "kmhhh", "fdohjehacdln", "e",
				"ojba", "ohadmod", "eaenkdiaokl", "dii", "cgfjaklblafeifo",
				"imoeflkcgbbem", "nbjkmb", "jjgm", "hofgelg",
				"cnihecmdigdgflg", "fnmikkeldjgb", "onlhgonldjaedh", "fmkdn",
				"kfbcbleen", "oejioibnmab", "cg", "meadghbocjnj",
				"hmmdnkegfeieijn", "ijgenomhndlje", "maccdcgfjig", "iabemie",
				"mlfg", "mdblmdaechmeaml", "dhlafgjo", "eabbiila", "kf",
				"oehggehfmijlfmg", "klljaejidfhbon", "akmbgmignoag",
				"jgbkngmigdfm", "kjeelnbn", "ajaa", "mlcjoiaahoiga",
				"oalnielba", "ffmobgkc", "kmhoknfffdmo", "nagjiffnjhh",
				"dlehllomjok", "agaejefhdbk", "nnegoijfdj", "ndl",
				"dhfginocgi", "nflmglgh", "bcd", "gbgjijemmdio", "jk",
				"gidgjbmb", "hi", "lmgoah", "fdebefcech", "ach", "bahaoj",
				"ccmmblgibgjahi", "moid", "jhilgedidndm", "ldiakemnj",
				"bbnibccm", "jkbneoaheaajnm", "clkgmbjlgdnl", "lobbdldifnnijh",
				"dnmih", "jglia", "didicmghfe", "dlhbcfclf", "akbmioocoihkfh",
				"foofdldm", "imenimfcame", "ifekbmgnbdkc", "jjlkaabdollola",
				"gie", "hbaj", "noomfnfccmgaa", "dcjffeg", "nb", "obdb",
				"lolgjflimkib", "eaiigminlakkb", "cia", "hkf", "jknfklaio",
				"igklbiomo", "jfjgh", "ekgnkfnhjcch", "kmonfcclieehlik",
				"oggkmccklnmj", "bedhobcl", "egmnhajcnhcdgb", "imfdhekamfel",
				"bmmkhfdbm", "gnjfbcjlecfn", "llmkgclm", "gafinbnhfe",
				"mlbfedkoeeddfao", "kklcdmglleb", "ckekmeiea", "mi", "kfejn",
				"lm", "mk", "abkoajocfdili", "jidac", "jonhkanccl",
				"lllodjgnmm", "abfeaodlmjkngol", "cdncnh", "lkcb",
				"abhilnmmhijab", "hiljkfakojjld", "mbboobkaolkljo",
				"jhkblobaofgoh", "ncm", "mgbdhmcicomf", "oag", "akmjdd",
				"abkenodnhj", "mljf", "afb", "afejkobmiffeee", "oollnkilabmb",
				"gfaocokmcmjlmb", "cokmecdd", "bo", "endocdnmjiek", "bcf",
				"lhllbagiel", "bhihgofhj", "ffce", "neio", "ofbfiiab", "kjdo",
				"lgfggnamceeo", "kofledoinamcj", "femhndomndoakoa",
				"fmodaigcka", "omakggcalhn", "hhhogmcbjnhelkk", "mgah",
				"jghjjfmk", "ecolelfmcb", "eajjkdncafhhgab", "obno",
				"fifigfeok", "laafjimienff", "beckbbmhmofb", "nafhihmgnikd",
				"cbcfnhlkne", "kao", "nlkfhbm", "fmh", "ohfek", "oj",
				"hifgcgi", "adhkn", "lffgmodeafnn", "ngchmhdbmhmhh",
				"mcffimhnlffab", "blhmkdhbnhbb", "kkb", "lgkine",
				"hgfbdbfffanhik", "joebhbh", "img", "kglcddmloo", "hoflgfao",
				"bdhgdekb", "mggflahnoo", "cmnol", "imnmmgimmedf",
				"mcjmoofomiia", "mlakhbjfnbmgena", "ilhmcnkkeg", "domhbmkcd",
				"fco", "fdio", "cmkoagblnd", "kmihfigmceiiicm", "afgbadbgbaon",
				"menahlemehifooe", "jacokdiiokaic", "limj", "fkedaoomokjbkdi",
				"jkncd", "jblmcmfnegnk", "jjicjhjhbg", "gbfcead", "jf",
				"aifnkmnao", "effmhlhchngknl", "odhjeib", "ohcgmgb", "bgbd",
				"am", "kkjfbdlh", "hgbjakkokjgooel", "jbeokiakf", "flaoba",
				"cifcdnanmk", "mice", "ihhofdai", "ldnfmeiemhf",
				"kefbbohhgineacj", "bi", "njfie", "ociodahlomoekkf",
				"andhoindeca", "ajnndjocjeg", "bmijkmjbbkgbbh", "feanh",
				"bjemcefkfcaenal", "edfdenghinm", "moal", "ndbjdmijh",
				"enccnhmoifa", "dbckadjibam", "gd", "oglj", "aldjelhbemle",
				"cmbkofkcoe", "ihciacibeh", "lcojkclhmibgoif", "jfmjncnolfj",
				"gfcmcabhjki", "aggfmakaanjb", "mhbelld", "hon",
				"nkfoikcddehcah", "kggbigknacmohb", "jbkgndofcmaaohh",
				"gkjano", "afhhhh", "mjng", "jilckm", "dekkedjehmenbm", "clfm",
				"acmhbkdadgena", "oenokachg", "lhiea", "dceiag", "eebgj",
				"oolifidh", "dj", "cdfn", "eghdglgiok", "jdhegkefhbdhkm",
				"mhgngafea", "akabbcjkdnbc", "gcbn", "kimdgahf", "oc" };

		HashSet<String> dict = new HashSet<String>();
		for (String str1 : d){
			dict.add(str1);
		}
		String str = "fajbeokiakfmlacbinjdnjdmmfha";
		System.out.println("start");
		long start = System.currentTimeMillis();
		boolean can = s.wordBreak(str, dict);
		long end = System.currentTimeMillis();
		System.out.println("end");
		System.out.println(can);
		System.out.println(end-start);
	}
}
