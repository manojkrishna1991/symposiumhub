<div class="ui modal" id="modal1" >
  <i class="close icon"></i>
  <div class="header">
    Edit Symposium
  </div>
 <div
				class="sixteen wide mobile eight wide tablet sixteen wide computer column">
				<form method="post" class="ui form step1"
					action="<c:url value='symposiumstep2' />"
					modelAttribute="symposium" action="">
					<h4 class="ui dividing header">
						<div class="ui message green">
							<div class="header">Basic Details</div>
						</div>
					</h4>
					<div class="field">
						<label>Symposium Name</label> <input type="text" name="name"
							placeholder="Symposium Name">


					</div>
					<div class="field">
						<label>College Name</label> <input type="text" name="collegeName"
							placeholder="Name of Your College">
					</div>
					<div class="two fields">
						<div class="field">
							<label>Department</label> <input type="text" name="department"
								placeholder="Department">
						</div>
						<div class="field">
							<label>Country</label>
							<div class="ui fluid search selection dropdown">
								<input type="hidden" name="country"> <i
									class="dropdown icon"></i>
								<div class="default text">Select Country</div>
								<div class="menu">
									<div class="item" data-value="af">
										<i class="af flag"></i>Afghanistan
									</div>
									<div class="item" data-value="ax">
										<i class="ax flag"></i>Aland Islands
									</div>
									<div class="item" data-value="al">
										<i class="al flag"></i>Albania
									</div>
									<div class="item" data-value="dz">
										<i class="dz flag"></i>Algeria
									</div>
									<div class="item" data-value="as">
										<i class="as flag"></i>American Samoa
									</div>
									<div class="item" data-value="ad">
										<i class="ad flag"></i>Andorra
									</div>
									<div class="item" data-value="ao">
										<i class="ao flag"></i>Angola
									</div>
									<div class="item" data-value="ai">
										<i class="ai flag"></i>Anguilla
									</div>
									<div class="item" data-value="ag">
										<i class="ag flag"></i>Antigua
									</div>
									<div class="item" data-value="ar">
										<i class="ar flag"></i>Argentina
									</div>
									<div class="item" data-value="am">
										<i class="am flag"></i>Armenia
									</div>
									<div class="item" data-value="aw">
										<i class="aw flag"></i>Aruba
									</div>
									<div class="item" data-value="au">
										<i class="au flag"></i>Australia
									</div>
									<div class="item" data-value="at">
										<i class="at flag"></i>Austria
									</div>
									<div class="item" data-value="az">
										<i class="az flag"></i>Azerbaijan
									</div>
									<div class="item" data-value="bs">
										<i class="bs flag"></i>Bahamas
									</div>
									<div class="item" data-value="bh">
										<i class="bh flag"></i>Bahrain
									</div>
									<div class="item" data-value="bd">
										<i class="bd flag"></i>Bangladesh
									</div>
									<div class="item" data-value="bb">
										<i class="bb flag"></i>Barbados
									</div>
									<div class="item" data-value="by">
										<i class="by flag"></i>Belarus
									</div>
									<div class="item" data-value="be">
										<i class="be flag"></i>Belgium
									</div>
									<div class="item" data-value="bz">
										<i class="bz flag"></i>Belize
									</div>
									<div class="item" data-value="bj">
										<i class="bj flag"></i>Benin
									</div>
									<div class="item" data-value="bm">
										<i class="bm flag"></i>Bermuda
									</div>
									<div class="item" data-value="bt">
										<i class="bt flag"></i>Bhutan
									</div>
									<div class="item" data-value="bo">
										<i class="bo flag"></i>Bolivia
									</div>
									<div class="item" data-value="ba">
										<i class="ba flag"></i>Bosnia
									</div>
									<div class="item" data-value="bw">
										<i class="bw flag"></i>Botswana
									</div>
									<div class="item" data-value="bv">
										<i class="bv flag"></i>Bouvet Island
									</div>
									<div class="item" data-value="br">
										<i class="br flag"></i>Brazil
									</div>
									<div class="item" data-value="vg">
										<i class="vg flag"></i>British Virgin Islands
									</div>
									<div class="item" data-value="bn">
										<i class="bn flag"></i>Brunei
									</div>
									<div class="item" data-value="bg">
										<i class="bg flag"></i>Bulgaria
									</div>
									<div class="item" data-value="bf">
										<i class="bf flag"></i>Burkina Faso
									</div>
									<div class="item" data-value="ar">
										<i class="ar flag"></i>Burma
									</div>
									<div class="item" data-value="bi">
										<i class="bi flag"></i>Burundi
									</div>
									<div class="item" data-value="tc">
										<i class="tc flag"></i>Caicos Islands
									</div>
									<div class="item" data-value="kh">
										<i class="kh flag"></i>Cambodia
									</div>
									<div class="item" data-value="cm">
										<i class="cm flag"></i>Cameroon
									</div>
									<div class="item" data-value="ca">
										<i class="ca flag"></i>Canada
									</div>
									<div class="item" data-value="cv">
										<i class="cv flag"></i>Cape Verde
									</div>
									<div class="item" data-value="ky">
										<i class="ky flag"></i>Cayman Islands
									</div>
									<div class="item" data-value="cf">
										<i class="cf flag"></i>Central African Republic
									</div>
									<div class="item" data-value="td">
										<i class="td flag"></i>Chad
									</div>
									<div class="item" data-value="cl">
										<i class="cl flag"></i>Chile
									</div>
									<div class="item" data-value="cn">
										<i class="cn flag"></i>China
									</div>
									<div class="item" data-value="cx">
										<i class="cx flag"></i>Christmas Island
									</div>
									<div class="item" data-value="cc">
										<i class="cc flag"></i>Cocos Islands
									</div>
									<div class="item" data-value="co">
										<i class="co flag"></i>Colombia
									</div>
									<div class="item" data-value="km">
										<i class="km flag"></i>Comoros
									</div>
									<div class="item" data-value="cg">
										<i class="cg flag"></i>Congo Brazzaville
									</div>
									<div class="item" data-value="cd">
										<i class="cd flag"></i>Congo
									</div>
									<div class="item" data-value="ck">
										<i class="ck flag"></i>Cook Islands
									</div>
									<div class="item" data-value="cr">
										<i class="cr flag"></i>Costa Rica
									</div>
									<div class="item" data-value="ci">
										<i class="ci flag"></i>Cote Divoire
									</div>
									<div class="item" data-value="hr">
										<i class="hr flag"></i>Croatia
									</div>
									<div class="item" data-value="cu">
										<i class="cu flag"></i>Cuba
									</div>
									<div class="item" data-value="cy">
										<i class="cy flag"></i>Cyprus
									</div>
									<div class="item" data-value="cz">
										<i class="cz flag"></i>Czech Republic
									</div>
									<div class="item" data-value="dk">
										<i class="dk flag"></i>Denmark
									</div>
									<div class="item" data-value="dj">
										<i class="dj flag"></i>Djibouti
									</div>
									<div class="item" data-value="dm">
										<i class="dm flag"></i>Dominica
									</div>
									<div class="item" data-value="do">
										<i class="do flag"></i>Dominican Republic
									</div>
									<div class="item" data-value="ec">
										<i class="ec flag"></i>Ecuador
									</div>
									<div class="item" data-value="eg">
										<i class="eg flag"></i>Egypt
									</div>
									<div class="item" data-value="sv">
										<i class="sv flag"></i>El Salvador
									</div>
									<div class="item" data-value="gb">
										<i class="gb flag"></i>England
									</div>
									<div class="item" data-value="gq">
										<i class="gq flag"></i>Equatorial Guinea
									</div>
									<div class="item" data-value="er">
										<i class="er flag"></i>Eritrea
									</div>
									<div class="item" data-value="ee">
										<i class="ee flag"></i>Estonia
									</div>
									<div class="item" data-value="et">
										<i class="et flag"></i>Ethiopia
									</div>
									<div class="item" data-value="eu">
										<i class="eu flag"></i>European Union
									</div>
									<div class="item" data-value="fk">
										<i class="fk flag"></i>Falkland Islands
									</div>
									<div class="item" data-value="fo">
										<i class="fo flag"></i>Faroe Islands
									</div>
									<div class="item" data-value="fj">
										<i class="fj flag"></i>Fiji
									</div>
									<div class="item" data-value="fi">
										<i class="fi flag"></i>Finland
									</div>
									<div class="item" data-value="fr">
										<i class="fr flag"></i>France
									</div>
									<div class="item" data-value="gf">
										<i class="gf flag"></i>French Guiana
									</div>
									<div class="item" data-value="pf">
										<i class="pf flag"></i>French Polynesia
									</div>
									<div class="item" data-value="tf">
										<i class="tf flag"></i>French Territories
									</div>
									<div class="item" data-value="ga">
										<i class="ga flag"></i>Gabon
									</div>
									<div class="item" data-value="gm">
										<i class="gm flag"></i>Gambia
									</div>
									<div class="item" data-value="ge">
										<i class="ge flag"></i>Georgia
									</div>
									<div class="item" data-value="de">
										<i class="de flag"></i>Germany
									</div>
									<div class="item" data-value="gh">
										<i class="gh flag"></i>Ghana
									</div>
									<div class="item" data-value="gi">
										<i class="gi flag"></i>Gibraltar
									</div>
									<div class="item" data-value="gr">
										<i class="gr flag"></i>Greece
									</div>
									<div class="item" data-value="gl">
										<i class="gl flag"></i>Greenland
									</div>
									<div class="item" data-value="gd">
										<i class="gd flag"></i>Grenada
									</div>
									<div class="item" data-value="gp">
										<i class="gp flag"></i>Guadeloupe
									</div>
									<div class="item" data-value="gu">
										<i class="gu flag"></i>Guam
									</div>
									<div class="item" data-value="gt">
										<i class="gt flag"></i>Guatemala
									</div>
									<div class="item" data-value="gw">
										<i class="gw flag"></i>Guinea-Bissau
									</div>
									<div class="item" data-value="gn">
										<i class="gn flag"></i>Guinea
									</div>
									<div class="item" data-value="gy">
										<i class="gy flag"></i>Guyana
									</div>
									<div class="item" data-value="ht">
										<i class="ht flag"></i>Haiti
									</div>
									<div class="item" data-value="hm">
										<i class="hm flag"></i>Heard Island
									</div>
									<div class="item" data-value="hn">
										<i class="hn flag"></i>Honduras
									</div>
									<div class="item" data-value="hk">
										<i class="hk flag"></i>Hong Kong
									</div>
									<div class="item" data-value="hu">
										<i class="hu flag"></i>Hungary
									</div>
									<div class="item" data-value="is">
										<i class="is flag"></i>Iceland
									</div>
									<div class="item" data-value="in">
										<i class="in flag"></i>India
									</div>
									<div class="item" data-value="io">
										<i class="io flag"></i>Indian Ocean Territory
									</div>
									<div class="item" data-value="id">
										<i class="id flag"></i>Indonesia
									</div>
									<div class="item" data-value="ir">
										<i class="ir flag"></i>Iran
									</div>
									<div class="item" data-value="iq">
										<i class="iq flag"></i>Iraq
									</div>
									<div class="item" data-value="ie">
										<i class="ie flag"></i>Ireland
									</div>
									<div class="item" data-value="il">
										<i class="il flag"></i>Israel
									</div>
									<div class="item" data-value="it">
										<i class="it flag"></i>Italy
									</div>
									<div class="item" data-value="jm">
										<i class="jm flag"></i>Jamaica
									</div>
									<div class="item" data-value="jp">
										<i class="jp flag"></i>Japan
									</div>
									<div class="item" data-value="jo">
										<i class="jo flag"></i>Jordan
									</div>
									<div class="item" data-value="kz">
										<i class="kz flag"></i>Kazakhstan
									</div>
									<div class="item" data-value="ke">
										<i class="ke flag"></i>Kenya
									</div>
									<div class="item" data-value="ki">
										<i class="ki flag"></i>Kiribati
									</div>
									<div class="item" data-value="kw">
										<i class="kw flag"></i>Kuwait
									</div>
									<div class="item" data-value="kg">
										<i class="kg flag"></i>Kyrgyzstan
									</div>
									<div class="item" data-value="la">
										<i class="la flag"></i>Laos
									</div>
									<div class="item" data-value="lv">
										<i class="lv flag"></i>Latvia
									</div>
									<div class="item" data-value="lb">
										<i class="lb flag"></i>Lebanon
									</div>
									<div class="item" data-value="ls">
										<i class="ls flag"></i>Lesotho
									</div>
									<div class="item" data-value="lr">
										<i class="lr flag"></i>Liberia
									</div>
									<div class="item" data-value="ly">
										<i class="ly flag"></i>Libya
									</div>
									<div class="item" data-value="li">
										<i class="li flag"></i>Liechtenstein
									</div>
									<div class="item" data-value="lt">
										<i class="lt flag"></i>Lithuania
									</div>
									<div class="item" data-value="lu">
										<i class="lu flag"></i>Luxembourg
									</div>
									<div class="item" data-value="mo">
										<i class="mo flag"></i>Macau
									</div>
									<div class="item" data-value="mk">
										<i class="mk flag"></i>Macedonia
									</div>
									<div class="item" data-value="mg">
										<i class="mg flag"></i>Madagascar
									</div>
									<div class="item" data-value="mw">
										<i class="mw flag"></i>Malawi
									</div>
									<div class="item" data-value="my">
										<i class="my flag"></i>Malaysia
									</div>
									<div class="item" data-value="mv">
										<i class="mv flag"></i>Maldives
									</div>
									<div class="item" data-value="ml">
										<i class="ml flag"></i>Mali
									</div>
									<div class="item" data-value="mt">
										<i class="mt flag"></i>Malta
									</div>
									<div class="item" data-value="mh">
										<i class="mh flag"></i>Marshall Islands
									</div>
									<div class="item" data-value="mq">
										<i class="mq flag"></i>Martinique
									</div>
									<div class="item" data-value="mr">
										<i class="mr flag"></i>Mauritania
									</div>
									<div class="item" data-value="mu">
										<i class="mu flag"></i>Mauritius
									</div>
									<div class="item" data-value="yt">
										<i class="yt flag"></i>Mayotte
									</div>
									<div class="item" data-value="mx">
										<i class="mx flag"></i>Mexico
									</div>
									<div class="item" data-value="fm">
										<i class="fm flag"></i>Micronesia
									</div>
									<div class="item" data-value="md">
										<i class="md flag"></i>Moldova
									</div>
									<div class="item" data-value="mc">
										<i class="mc flag"></i>Monaco
									</div>
									<div class="item" data-value="mn">
										<i class="mn flag"></i>Mongolia
									</div>
									<div class="item" data-value="me">
										<i class="me flag"></i>Montenegro
									</div>
									<div class="item" data-value="ms">
										<i class="ms flag"></i>Montserrat
									</div>
									<div class="item" data-value="ma">
										<i class="ma flag"></i>Morocco
									</div>
									<div class="item" data-value="mz">
										<i class="mz flag"></i>Mozambique
									</div>
									<div class="item" data-value="na">
										<i class="na flag"></i>Namibia
									</div>
									<div class="item" data-value="nr">
										<i class="nr flag"></i>Nauru
									</div>
									<div class="item" data-value="np">
										<i class="np flag"></i>Nepal
									</div>
									<div class="item" data-value="an">
										<i class="an flag"></i>Netherlands Antilles
									</div>
									<div class="item" data-value="nl">
										<i class="nl flag"></i>Netherlands
									</div>
									<div class="item" data-value="nc">
										<i class="nc flag"></i>New Caledonia
									</div>
									<div class="item" data-value="pg">
										<i class="pg flag"></i>New Guinea
									</div>
									<div class="item" data-value="nz">
										<i class="nz flag"></i>New Zealand
									</div>
									<div class="item" data-value="ni">
										<i class="ni flag"></i>Nicaragua
									</div>
									<div class="item" data-value="ne">
										<i class="ne flag"></i>Niger
									</div>
									<div class="item" data-value="ng">
										<i class="ng flag"></i>Nigeria
									</div>
									<div class="item" data-value="nu">
										<i class="nu flag"></i>Niue
									</div>
									<div class="item" data-value="nf">
										<i class="nf flag"></i>Norfolk Island
									</div>
									<div class="item" data-value="kp">
										<i class="kp flag"></i>North Korea
									</div>
									<div class="item" data-value="mp">
										<i class="mp flag"></i>Northern Mariana Islands
									</div>
									<div class="item" data-value="no">
										<i class="no flag"></i>Norway
									</div>
									<div class="item" data-value="om">
										<i class="om flag"></i>Oman
									</div>
									<div class="item" data-value="pk">
										<i class="pk flag"></i>Pakistan
									</div>
									<div class="item" data-value="pw">
										<i class="pw flag"></i>Palau
									</div>
									<div class="item" data-value="ps">
										<i class="ps flag"></i>Palestine
									</div>
									<div class="item" data-value="pa">
										<i class="pa flag"></i>Panama
									</div>
									<div class="item" data-value="py">
										<i class="py flag"></i>Paraguay
									</div>
									<div class="item" data-value="pe">
										<i class="pe flag"></i>Peru
									</div>
									<div class="item" data-value="ph">
										<i class="ph flag"></i>Philippines
									</div>
									<div class="item" data-value="pn">
										<i class="pn flag"></i>Pitcairn Islands
									</div>
									<div class="item" data-value="pl">
										<i class="pl flag"></i>Poland
									</div>
									<div class="item" data-value="pt">
										<i class="pt flag"></i>Portugal
									</div>
									<div class="item" data-value="pr">
										<i class="pr flag"></i>Puerto Rico
									</div>
									<div class="item" data-value="qa">
										<i class="qa flag"></i>Qatar
									</div>
									<div class="item" data-value="re">
										<i class="re flag"></i>Reunion
									</div>
									<div class="item" data-value="ro">
										<i class="ro flag"></i>Romania
									</div>
									<div class="item" data-value="ru">
										<i class="ru flag"></i>Russia
									</div>
									<div class="item" data-value="rw">
										<i class="rw flag"></i>Rwanda
									</div>
									<div class="item" data-value="sh">
										<i class="sh flag"></i>Saint Helena
									</div>
									<div class="item" data-value="kn">
										<i class="kn flag"></i>Saint Kitts and Nevis
									</div>
									<div class="item" data-value="lc">
										<i class="lc flag"></i>Saint Lucia
									</div>
									<div class="item" data-value="pm">
										<i class="pm flag"></i>Saint Pierre
									</div>
									<div class="item" data-value="vc">
										<i class="vc flag"></i>Saint Vincent
									</div>
									<div class="item" data-value="ws">
										<i class="ws flag"></i>Samoa
									</div>
									<div class="item" data-value="sm">
										<i class="sm flag"></i>San Marino
									</div>
									<div class="item" data-value="gs">
										<i class="gs flag"></i>Sandwich Islands
									</div>
									<div class="item" data-value="st">
										<i class="st flag"></i>Sao Tome
									</div>
									<div class="item" data-value="sa">
										<i class="sa flag"></i>Saudi Arabia
									</div>
									<div class="item" data-value="sn">
										<i class="sn flag"></i>Senegal
									</div>
									<div class="item" data-value="cs">
										<i class="cs flag"></i>Serbia
									</div>
									<div class="item" data-value="rs">
										<i class="rs flag"></i>Serbia
									</div>
									<div class="item" data-value="sc">
										<i class="sc flag"></i>Seychelles
									</div>
									<div class="item" data-value="sl">
										<i class="sl flag"></i>Sierra Leone
									</div>
									<div class="item" data-value="sg">
										<i class="sg flag"></i>Singapore
									</div>
									<div class="item" data-value="sk">
										<i class="sk flag"></i>Slovakia
									</div>
									<div class="item" data-value="si">
										<i class="si flag"></i>Slovenia
									</div>
									<div class="item" data-value="sb">
										<i class="sb flag"></i>Solomon Islands
									</div>
									<div class="item" data-value="so">
										<i class="so flag"></i>Somalia
									</div>
									<div class="item" data-value="za">
										<i class="za flag"></i>South Africa
									</div>
									<div class="item" data-value="kr">
										<i class="kr flag"></i>South Korea
									</div>
									<div class="item" data-value="es">
										<i class="es flag"></i>Spain
									</div>
									<div class="item" data-value="lk">
										<i class="lk flag"></i>Sri Lanka
									</div>
									<div class="item" data-value="sd">
										<i class="sd flag"></i>Sudan
									</div>
									<div class="item" data-value="sr">
										<i class="sr flag"></i>Suriname
									</div>
									<div class="item" data-value="sj">
										<i class="sj flag"></i>Svalbard
									</div>
									<div class="item" data-value="sz">
										<i class="sz flag"></i>Swaziland
									</div>
									<div class="item" data-value="se">
										<i class="se flag"></i>Sweden
									</div>
									<div class="item" data-value="ch">
										<i class="ch flag"></i>Switzerland
									</div>
									<div class="item" data-value="sy">
										<i class="sy flag"></i>Syria
									</div>
									<div class="item" data-value="tw">
										<i class="tw flag"></i>Taiwan
									</div>
									<div class="item" data-value="tj">
										<i class="tj flag"></i>Tajikistan
									</div>
									<div class="item" data-value="tz">
										<i class="tz flag"></i>Tanzania
									</div>
									<div class="item" data-value="th">
										<i class="th flag"></i>Thailand
									</div>
									<div class="item" data-value="tl">
										<i class="tl flag"></i>Timorleste
									</div>
									<div class="item" data-value="tg">
										<i class="tg flag"></i>Togo
									</div>
									<div class="item" data-value="tk">
										<i class="tk flag"></i>Tokelau
									</div>
									<div class="item" data-value="to">
										<i class="to flag"></i>Tonga
									</div>
									<div class="item" data-value="tt">
										<i class="tt flag"></i>Trinidad
									</div>
									<div class="item" data-value="tn">
										<i class="tn flag"></i>Tunisia
									</div>
									<div class="item" data-value="tr">
										<i class="tr flag"></i>Turkey
									</div>
									<div class="item" data-value="tm">
										<i class="tm flag"></i>Turkmenistan
									</div>
									<div class="item" data-value="tv">
										<i class="tv flag"></i>Tuvalu
									</div>
									<div class="item" data-value="ug">
										<i class="ug flag"></i>Uganda
									</div>
									<div class="item" data-value="ua">
										<i class="ua flag"></i>Ukraine
									</div>
									<div class="item" data-value="ae">
										<i class="ae flag"></i>United Arab Emirates
									</div>
									<div class="item" data-value="us">
										<i class="us flag"></i>United States
									</div>
									<div class="item" data-value="uy">
										<i class="uy flag"></i>Uruguay
									</div>
									<div class="item" data-value="um">
										<i class="um flag"></i>Us Minor Islands
									</div>
									<div class="item" data-value="vi">
										<i class="vi flag"></i>Us Virgin Islands
									</div>
									<div class="item" data-value="uz">
										<i class="uz flag"></i>Uzbekistan
									</div>
									<div class="item" data-value="vu">
										<i class="vu flag"></i>Vanuatu
									</div>
									<div class="item" data-value="va">
										<i class="va flag"></i>Vatican City
									</div>
									<div class="item" data-value="ve">
										<i class="ve flag"></i>Venezuela
									</div>
									<div class="item" data-value="vn">
										<i class="vn flag"></i>Vietnam
									</div>
									<div class="item" data-value="wf">
										<i class="wf flag"></i>Wallis and Futuna
									</div>
									<div class="item" data-value="eh">
										<i class="eh flag"></i>Western Sahara
									</div>
									<div class="item" data-value="ye">
										<i class="ye flag"></i>Yemen
									</div>
									<div class="item" data-value="zm">
										<i class="zm flag"></i>Zambia
									</div>
									<div class="item" data-value="zw">
										<i class="zw flag"></i>Zimbabwe
									</div>
								</div>
							</div>
						</div>
					</div>
					<h4 class="ui dividing header">
						<div class="ui message green">
							<div class="header">Contact and Venue Info</div>
					</h4>
					<div class="field">
						<div class="fields">
							<div class="three wide field">
							
								<label>Event Date</label> 
								<div class="ui icon input">
								<input type="text" id="datepicker"
									name="dateOfEvent">
									<i class="calendar icon"></i>
									</div>
							</div>
						</div>
					</div>
					<div class="eight wide field">
						<label>Venue Address</label>
						<textarea name="address"></textarea>
					</div>
					<div class="field">

					<div class="ui form">
  <div class="three fields">
    <div class="field">
      <label>Coordinator Name(1)</label>
      <input type="text" id="cname" name="coordinators[0].name" placeholder="Name">
    </div>
    <div class="field">
      <label>Coordinator Email(1)</label>
      <input type="text" id="cemailid" name="coordinators[0].emailid" placeholder="Email">
    </div>
    <div class="field">
      <label>Coordinator Mobile No(1)</label>
      <input type="text" id="cphoneno" name="coordinators[0].phoneno" placeholder="Mobile no">
    </div>
  </div>
</div>
</div>
	<div class="field">
<div class="ui form">
  <div class="three fields">
    <div class="field">
      <label>Coordinator Name(2)</label>
      <input type="text" name="coordinators[1].name" placeholder="Name">
    </div>
    <div class="field">
      <label>Coordinator Email(2)</label>
      <input type="text" name="coordinators[1].emailid" placeholder="Email">
    </div>
    <div class="field">
      <label>Coordinator Mobile No(2)</label>
      <input type="text" name="coordinators[1].phoneno" placeholder="Mobile no">
    </div>
  </div>
</div>


				

					

					</div>
					<div class="eight wide field">
						<label>Website Link</label> 
						<div class="ui icon input">
						<input type="text" name="webAddress">
						<i class="linkify icon"></i>
						</div>
						
					</div>
					<h4 class="ui dividing header">
						<div class="ui message green">
							<div class="header">Important Dates ,Events & Paper Titles
							</div>
					</h4>
					<div class="field">
						<div class="inline fields">
							<div class="five wide field">
								<label style="width:100%" >Last date for paper submission</label>
								<div class="ui icon input">
								 <input type="text" id="datepicker1"
									 name="fromDate">

<i class="calendar icon"></i>
</div>
							</div>
							<div class="six wide field">
								<label style="width:95%">Selected papers are informed before</label> 
								<div class="ui icon input">
								
								<input type="text" id="datepicker2"
									name="toDate">

<i class="calendar icon"></i>
</div>
							</div>

						</div>

					</div>
					<div class="field">
						<label>Events</label> <input name="events" type="hidden"
							id="mySingleField" value="CircuitX,Circuit Debugging">
						<!-- only disabled for demonstration purposes -->

						<ul id="singleFieldTags"></ul>
					</div>

					<div class="field">
						<label>Paper Titles</label> <input name="symposiumPapers"
							type="hidden" id="mySingleField1"
							value="Robotics,Artificial Intelligence">
						<!-- only disabled for demonstration purposes -->

						<ul id="singleFieldTags1"></ul>
					</div>

					<div class="eight wide field">
						<label>Important Information </label>
						<textarea name="impDescription"></textarea>
					</div>
					<h4 class="ui dividing header">
						<div class="ui message green">
							<div class="header">Registration recipient</div>
					</h4>
					<div class="six wide field">
						<label>Send Registration Email To:</label> <input type="email"
							name="regEmail">
					</div>
					<div class="ui blue submit button">Submit</div>
					<div class="ui error message"></div>
				</form>
			</div>
</div>

<script>
	$(function() {

		$('.ui.form.step1').form({
			fields : {
				name : 'empty',
				collegeName : 'empty',
				department : 'empty',
				dateOfEvent : 'empty',
				country : 'empty',
				address : 'empty',
				cname: 'empty',
				cemailid:[ 'empty', 'email' ],
				cphoneno:[ 'empty', 'number' ],
				regEmail : [ 'empty', 'email' ]
			},
			inline : true,
			on : 'blur'
		});
		$("#datepicker").datepicker();
	});
	$(function() {
		$("#datepicker1").datepicker();
	});
	$(function() {
		$("#datepicker2").datepicker();
	});
	$(function() {
		var sampleTags = [ 'c++', 'java', 'php', 'coldfusion', 'javascript',
				'asp', 'ruby', 'python', 'c', 'scala', 'groovy', 'haskell',
				'perl', 'erlang', 'apl', 'cobol', 'go', 'lua' ];
		$('#singleFieldTags').tagit({
			availableTags : sampleTags,
			// This will make Tag-it submit a single form value, as a comma-delimited field.
			singleField : true,
			singleFieldNode : $('#mySingleField')
		});

		// singleFieldTags2 is an INPUT element, rather than a UL as in the other 
		// examples, so it automatically defaults to singleField.
		$('#singleFieldTags2').tagit({
			availableTags : sampleTags
		});
		$('#singleFieldTags1').tagit({
			availableTags : sampleTags,
			// This will make Tag-it submit a single form value, as a comma-delimited field.
			singleField : true,
			singleFieldNode : $('#mySingleField1')
		});

		// singleFieldTags2 is an INPUT element, rather than a UL as in the other 
		// examples, so it automatically defaults to singleField.
		$('#singleFieldTags21').tagit({
			availableTags : sampleTags
		});
	});

	$('.ui.checkbox').checkbox();
	$(document).ready(function() {
		$('.ui.selection.dropdown').dropdown();
		$('.ui.menu .ui.dropdown').dropdown({
			on : 'hover'
		});
	});
	$( "#edit" ).click(function() {
		$('#modal1').modal('show');
	});
</script>