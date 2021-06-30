package Project2;
/* -----------------------Read Me------------------------
 * 
 * 	학교 인터넷으로는 op.gg 라는 사이트는 접속이 불가 합니다.
 * 	가능하시면 휴대폰의 데이터 ( 핫스팟 )이나 접속이 가능한 인
 * 	터넷을 사용해주세요.
 * 
 * ------------------------------------------------------
 * */




import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Class1 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String nickname = in.nextLine();
		
		File file = new File("data/result.html");
		try {
			
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);
			Document doc = Jsoup.connect("https://www.op.gg/summoner/userName=" + nickname).get();
			Elements Kate = doc.select(".WinRatioGraph > .Text");
			Elements SoloRank = doc.select(".TierRank");
			Elements SubRank = doc.select(".sub-tier__rank-tier");
			Elements KDA = doc.select("td.KDA > div.KDARatio > span.KDARatio");
			Elements Kill = doc.select("td.KDA > div.KDA > span.Kill");
			Elements Death = doc.select("td.KDA > div.KDA > span.Death");
			Elements Assist = doc.select("td.KDA > div.KDA > span.Assist");
			Elements RANK = doc.select(".ranking");
			bw.write("<div style='height: 100%; text-align: center'>");
			bw.write("<h1>JAVA WEB 크롤링</h1>");
			bw.write("<h1>닉네임 : "+nickname+"</h1>");
			
			if (RANK.size() > 0) {
				bw.write("<h1> 래더 랭킹" + RANK.get(0).text() + "위</h1>");				
			}else {
				bw.write("<h1>래더 랭킹이 표시 되지 않았습니다.</h1>");
			}
			bw.write("<h1>승률 : " + Kate.get(0).text()+ "</h1>");
			bw.write("<h1>solo Rank : " + SoloRank.get(0).text()+ "</h1>");
			bw.write("<h1>sub (자유 5:5) Rank : " + SubRank.get(0).text()+"</h1>");
			bw.write("<h1>K.D.A : "+ KDA.get(0).text() +"</h1>");
			bw.write("<h1>K.D.A FULL : "+ Kill.get(0).text() +"/" + Death.get(0).text() + "/" + Assist.get(0).text() + "</h1>");
			bw.write("</div>");
			
			bw.close();
			osw.close();
			fos.close();
		
		} catch(Exception e) {
			System.out.println("Error" + e);
		}
	}

}
