import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Parser {

    static ArrayList<Object> objects = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        objects.add(new Object("1"));
        String url = "https://www.avito.ru/kazan/tovary_dlya_kompyutera/komplektuyuschie/videokarty-ASgBAgICAkTGB~pm7gmmZw?f=ASgBAQICAkTGB~pm7gmmZwFA~LwNFJjSNA&s=104&user=1";
        Document page = Jsoup.parse(new URL(url), 999999999);
        for (int i = 0; ; i++) {
            try {
                Elements el = page.select("div[class=items-vip-KXPvy]").remove();
                String a = page.select("div[class=index-content-_KxNP]").select("div[class=index-root-KVurS]").select("div[class=items-items-kAJAg]").select("div[data-marker=item]").get(i).select("div[class=iva-item-content-rejJg]").select("div[class=iva-item-body-KLUuy]").select("div[class=iva-item-dateInfoStep-_acjp]").text();
                String b = page.select("div[class=index-content-_KxNP]").select("div[class=index-root-KVurS]").select("div[class=items-items-kAJAg]").select("div[data-marker=item]").get(i).select("div[class=iva-item-content-rejJg]").select("div[class=iva-item-slider-pYwHo]").select("a").attr("href").replace("/kazan/tovary_dlya_kompyutera/", "https://www.avito.ru/kazan/tovary_dlya_kompyutera/");
                if (a.equals("1 час назад") || a.equals("2 часа назад")) {
                    CheckObject(b);
                }
            } catch (Exception e) {
                break;
            }
        }
        Main.objects2 = objects;
    }
    private static void CheckObject (String link){
        boolean flag = false;

        for (int i = 0; ; i++) {
            try {
                if (objects.get(i).Link.equals(link)) {
                    flag = true;
                }
            } catch (Exception e) {
                break;
            }
        }
        if(!flag){
            objects.add(new Object(link));
        }
    }
}
