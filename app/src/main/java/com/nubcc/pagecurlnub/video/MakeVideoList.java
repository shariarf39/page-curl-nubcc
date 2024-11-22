
package com.nubcc.pagecurlnub.video;

import com.nubcc.pagecurlnub.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MakeVideoList {

	public static ArrayList< ArrayList<HashMap<String,String>> > rootArrayList;
	public static ArrayList< HashMap<String, String> > catArrayList;
	public static ArrayList< HashMap<String, String> > videoArrayList;
	public static HashMap<String, String> hashMap;


	//--------------------------------------------------------------------------------------------
	//===============================Some automation by Juba
	public static void addVideoItem(String video_id, String title, String desciption){
		hashMap = new HashMap<>();
		hashMap.put("vdo_id", video_id);
		hashMap.put("vdo_title", title);
		hashMap.put("vdo_desciption", desciption);
		videoArrayList.add(hashMap);
	}
	//========================================================================
	//===============================Some automation by Juba
	public static void createPlayList(String category_name, Integer drawable){
		rootArrayList.add(videoArrayList);
		hashMap = new HashMap<>();
		hashMap.put("category_name", category_name);
		hashMap.put("img", String.valueOf(drawable));
		catArrayList.add(hashMap);
		videoArrayList = new ArrayList<>();
	}
	//========================================================================





	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>
	//----------------------------------------------------
	public static void createMyAlbums(){

		rootArrayList = new ArrayList();
		catArrayList = new ArrayList<>();
		videoArrayList = new ArrayList<>();

		//==========================================================================
		addVideoItem("ee7AxUAlmI8", "The bloody July \uD83D\uDC94", "The bloody July \uD83D\uDC94");
		addVideoItem("zpqSVC3WmxE", "আন্দোলন \uD83C\uDDE7\uD83C\uDDE9", "আন্দোলন \uD83C\uDDE7\uD83C\uDDE9");
		addVideoItem("104FvtnftKE", "স্বাধীন বাংলাদেশ \uD83C\uDDE7\uD83C\uDDE9", "স্বাধীন বাংলাদেশ \uD83C\uDDE7\uD83C\uDDE9");
		addVideoItem("rdB5p_JNi7A", "Northern University Bangladesh ", "Northern University Bangladesh ");
		addVideoItem("sUHar_DL0Bw", "আন্দোলন", "আন্দোলন");
		createPlayList("NUBCC", R.drawable.nubcc_magazine_01);
		//==========================================================================

		//==========================================================================
		addVideoItem("qPna1ZlRxTo", "Allah di kasam tu mainu enna pyara ho gaya", "Teri har cheez jannat ae -hasna vi jannt ae");
		createPlayList("NUBCC", R.drawable.nubcc_magazine_01);
		//==========================================================================















	}

	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>




}

