package dream.fata.fat;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AdminResultActivity extends AppCompatActivity {
	private String OldBal = "";
	private String WinEmail = "";
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private ChildEventListener _ff_results_child_listener;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private ChildEventListener _playNow_Digits_child_listener;
	private ChildEventListener _play_now_child_listener;
	private Timer _timer = new Timer();
	private Toolbar _toolbar;
	private ChildEventListener _user_h_child_listener;
	private ChildEventListener _user_logGames_child_listener;
	private ChildEventListener _users_child_listener;
	private String bazi_name = "";
	private double bazi_number = 0.0d;
	private ImageView button_back;
	private ImageView button_next;
	private Button button_win;
	private Calendar c = Calendar.getInstance();
	private String ccopyval2 = "";
	private String complete = "";
	private String copy = "";
	private String copyval1 = "";
	private double current_time = 0.0d;
	private DatabaseReference ff_results = this._firebase.getReference("ff_results");
	private String game = "";
	private String game_sp = "";
	private LinearLayout l_win;
	private LinearLayout linear1;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap_f = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap_playNow = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap_playnow_d = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap_users = new ArrayList<>();
	private ArrayList<Double> listnumber = new ArrayList<>();
	private ListView listview1;
	private HashMap<String, Object> map_r = new HashMap<>();
	private double n_f = 0.0d;
	private double n_r = 0.0d;
	private String pathWinner = "";
	private String pathWinner2 = "";
	private String path_playNow = "";
	private String path_playNow2 = "";
	private ProgressDialog pd;
	private DatabaseReference playNow_Digits = this._firebase.getReference("playNow_Digits");
	private DatabaseReference play_now = this._firebase.getReference("play_now");
	private double rs = 0.0d;
	public HashMap<Double, Double> sample_List = new HashMap<>();
	private String saved = "";
	private String str = "";
	private TimerTask t;
	private TimerTask t2;
	private double temp = 0.0d;
	private TextView textview1;
	private TextView textview2;
	private TextView textview8;
	private TimerTask tt;
	private TimerTask ttt;
	private TimerTask lllt;
	private TimerTask tttt;
	private DatabaseReference user_h = this._firebase.getReference("user_h");
	private DatabaseReference user_logGames = this._firebase.getReference("user_logGames");
	private DatabaseReference users = this._firebase.getReference("users");
	private double win_n = 0.0d;
	private double win_r_n;
	private EditText win_value;
	private String winrs = "";

	static /* synthetic */ double access$2308(AdminResultActivity x0) {
		double d = x0.win_r_n;
		x0.win_r_n = 1.0d + d;
		return d;
	}

	static /* synthetic */ double access$308(AdminResultActivity x0) {
		double d = x0.bazi_number;
		x0.bazi_number = 1.0d + d;
		return d;
	}

	static /* synthetic */ double access$310(AdminResultActivity x0) {
		double d = x0.bazi_number;
		x0.bazi_number = d - 1.0d;
		return d;
	}

	static /* synthetic */ double access$4108(AdminResultActivity x0) {
		double d = x0.n_f;
		x0.n_f = 1.0d + d;
		return d;
	}

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.admin_result);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}

	private void initialize(Bundle _savedInstanceState) {
		this._app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		this._coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		Toolbar toolbar = (Toolbar) findViewById(R.id._toolbar);
		this._toolbar = toolbar;
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		this._toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.AdminResultActivity.AnonymousClass1 */

			public void onClick(View _v) {
				AdminResultActivity.this.onBackPressed();
			}
		});
		this.linear1 = (LinearLayout) findViewById(R.id.linear1);
		this.listview1 = (ListView) findViewById(R.id.listview1);
		this.textview8 = (TextView) findViewById(R.id.textview8);
		this.textview1 = (TextView) findViewById(R.id.textview1);
		this.textview2 = (TextView) findViewById(R.id.textview2);
		this.button_back = (ImageView) findViewById(R.id.button_back);
		this.button_next = (ImageView) findViewById(R.id.button_next);
		this.button_win = (Button) findViewById(R.id.button_win);
		this.l_win = (LinearLayout) findViewById(R.id.l_win);
		this.win_value = (EditText) findViewById(R.id.win_value);
		this.button_next.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.AdminResultActivity.AnonymousClass2 */

			public void onClick(View _view) {
				AdminResultActivity.this.pd.setMessage("Loading");
				AdminResultActivity.this.pd.setCancelable(false);
				AdminResultActivity.this.pd.show();
				AdminResultActivity adminResultActivity = AdminResultActivity.this;
				adminResultActivity.game = adminResultActivity.getIntent().getStringExtra("Game");
				AdminResultActivity adminResultActivity2 = AdminResultActivity.this;
				adminResultActivity2.game_sp = adminResultActivity2.getIntent().getStringExtra("Game_sp");
				AdminResultActivity.access$308(AdminResultActivity.this);
				AdminResultActivity adminResultActivity3 = AdminResultActivity.this;
				adminResultActivity3.bazi_name = String.valueOf((long) adminResultActivity3.bazi_number).concat("BAZI");
				AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
				if (AdminResultActivity.this.getIntent().getStringExtra("Game").equals("Main Bazar")) {
					AdminResultActivity.this.bazi_name = "CLOSE";
					AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
				}
				AdminResultActivity.this.t2 = new TimerTask() {
					/* class dream.fata.fat.AdminResultActivity.AnonymousClass2.AnonymousClass1 */

					public void run() {
						AdminResultActivity.this.runOnUiThread(new Runnable() {
							/* class dream.fata.fat.AdminResultActivity.AnonymousClass2.AnonymousClass1.AnonymousClass1 */

							public void run() {
								AdminResultActivity.this.c.setTimeInMillis(AdminResultActivity.this.c.getTimeInMillis() - 7200000);
								AdminResultActivity.this.path_playNow = "playNow_".concat(AdminResultActivity.this.game_sp.toLowerCase().concat("/".concat(AdminResultActivity.this.game.concat("/".concat(new SimpleDateFormat("dd-MM-yyyy").format(AdminResultActivity.this.c.getTime()).concat("/".concat(AdminResultActivity.this.bazi_name)))))));
								AdminResultActivity.this.c = Calendar.getInstance();
								AdminResultActivity.this.play_now.removeEventListener(AdminResultActivity.this._play_now_child_listener);
								AdminResultActivity.this.play_now = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.path_playNow);
								AdminResultActivity.this.play_now.addChildEventListener(AdminResultActivity.this._play_now_child_listener);
								AdminResultActivity.this.play_now.addListenerForSingleValueEvent(new ValueEventListener() {
									public void onCancelled(DatabaseError param4DatabaseError) {}

									public void onDataChange(DataSnapshot param4DataSnapshot) {
										listmap_playNow = new ArrayList();
										try {
											GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

											};
											Iterator<DataSnapshot> iterator = param4DataSnapshot.getChildren().iterator();
											while (iterator.hasNext()) {
												HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
												AdminResultActivity.this.listmap_playNow.add(hashMap);
											}
											AdminResultActivity.this.CalculateRS(AdminResultActivity.this.listmap_playNow);
											return;
										} catch (Exception exception) {
											exception.printStackTrace();
											return;
										}
									}
								});
								AdminResultActivity.this.c.setTimeInMillis(AdminResultActivity.this.c.getTimeInMillis() - 7200000);
								AdminResultActivity.this.path_playNow2 = "playNow_".concat(AdminResultActivity.this.game_sp.toLowerCase().concat("_Digits/".concat(AdminResultActivity.this.game.concat("/".concat(new SimpleDateFormat("dd-MM-yyyy").format(AdminResultActivity.this.c.getTime()).concat("/".concat(AdminResultActivity.this.bazi_name)))))));
								AdminResultActivity.this.c = Calendar.getInstance();
								AdminResultActivity.this.playNow_Digits.removeEventListener(AdminResultActivity.this._playNow_Digits_child_listener);
								AdminResultActivity.this.playNow_Digits = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.path_playNow2);
								AdminResultActivity.this.playNow_Digits.addChildEventListener(AdminResultActivity.this._playNow_Digits_child_listener);
								AdminResultActivity.this.playNow_Digits.addListenerForSingleValueEvent(new ValueEventListener() {
									public void onCancelled(DatabaseError param4DatabaseError) {}

									public void onDataChange(DataSnapshot param4DataSnapshot) {
										listmap_playnow_d = new ArrayList();
										try {
											GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

											};
											Iterator<DataSnapshot> iterator = param4DataSnapshot.getChildren().iterator();
											while (iterator.hasNext()) {
												HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
												AdminResultActivity.this.listmap_playnow_d.add(hashMap);
											}
										} catch (Exception exception) {
											exception.printStackTrace();
										}
										if (AdminResultActivity.this.listmap_playnow_d.size() == 0) {
											AdminResultActivity.this.l_win.setVisibility(View.GONE);
										} else {
											AdminResultActivity.this.l_win.setVisibility(View.VISIBLE);
										}
										AdminResultActivity.this.listview1.setAdapter((ListAdapter)new AdminResultActivity.Listview1Adapter(AdminResultActivity.this.listmap_playnow_d));
										((BaseAdapter)AdminResultActivity.this.listview1.getAdapter()).notifyDataSetChanged();
										AdminResultActivity.this.pd.dismiss();
									}
								});
							}
						});
					}
				};
				AdminResultActivity.this._timer.schedule(AdminResultActivity.this.t2, 3000);
			}
		});
		this.button_back.setOnClickListener(new View.OnClickListener() {


			public void onClick(View _view) {
				if (AdminResultActivity.this.bazi_number != 1.0d) {
					AdminResultActivity.this.pd.setMessage("Loading");
					AdminResultActivity.this.pd.setCancelable(false);
					AdminResultActivity.this.pd.show();
					AdminResultActivity adminResultActivity = AdminResultActivity.this;
					adminResultActivity.game = adminResultActivity.getIntent().getStringExtra("Game");
					AdminResultActivity adminResultActivity2 = AdminResultActivity.this;
					adminResultActivity2.game_sp = adminResultActivity2.getIntent().getStringExtra("Game_sp");
					AdminResultActivity.access$310(AdminResultActivity.this);
					AdminResultActivity adminResultActivity3 = AdminResultActivity.this;
					adminResultActivity3.bazi_name = String.valueOf((long) adminResultActivity3.bazi_number).concat("BAZI");
					AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
					if (AdminResultActivity.this.getIntent().getStringExtra("Game").equals("Main Bazar")) {
						AdminResultActivity.this.bazi_name = "OPEN";
						AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
					}
					AdminResultActivity.this.t2 = new TimerTask() {
						/* class dream.fata.fat.AdminResultActivity.AnonymousClass3.AnonymousClass1 */

						public void run() {
							AdminResultActivity.this.runOnUiThread(new Runnable() {
								/* class dream.fata.fat.AdminResultActivity.AnonymousClass3.AnonymousClass1.AnonymousClass1 */

								public void run() {
									AdminResultActivity.this.c.setTimeInMillis(AdminResultActivity.this.c.getTimeInMillis() - 7200000);
									AdminResultActivity.this.path_playNow = "playNow_".concat(AdminResultActivity.this.game_sp.toLowerCase().concat("/".concat(AdminResultActivity.this.game.concat("/".concat(new SimpleDateFormat("dd-MM-yyyy").format(AdminResultActivity.this.c.getTime()).concat("/".concat(AdminResultActivity.this.bazi_name)))))));
									AdminResultActivity.this.c = Calendar.getInstance();
									AdminResultActivity.this.play_now.removeEventListener(AdminResultActivity.this._play_now_child_listener);
									AdminResultActivity.this.play_now = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.path_playNow);
									AdminResultActivity.this.play_now.addChildEventListener(AdminResultActivity.this._play_now_child_listener);
									AdminResultActivity.this.play_now.addListenerForSingleValueEvent(new ValueEventListener() {
										public void onCancelled(DatabaseError param4DatabaseError) {}

										public void onDataChange(DataSnapshot param4DataSnapshot) {
											listmap_playNow = new ArrayList();
											try {
												GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

												};
												Iterator<DataSnapshot> iterator = param4DataSnapshot.getChildren().iterator();
												while (iterator.hasNext()) {
													HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
													AdminResultActivity.this.listmap_playNow.add(hashMap);
												}
												AdminResultActivity.this.CalculateRS(AdminResultActivity.this.listmap_playNow);
												return;
											} catch (Exception exception) {
												exception.printStackTrace();
												return;
											}
										}
									});
									AdminResultActivity.this.c.setTimeInMillis(AdminResultActivity.this.c.getTimeInMillis() - 7200000);
									AdminResultActivity.this.path_playNow2 = "playNow_".concat(AdminResultActivity.this.game_sp.toLowerCase().concat("_Digits/".concat(AdminResultActivity.this.game.concat("/".concat(new SimpleDateFormat("dd-MM-yyyy").format(AdminResultActivity.this.c.getTime()).concat("/".concat(AdminResultActivity.this.bazi_name)))))));
									AdminResultActivity.this.c = Calendar.getInstance();
									AdminResultActivity.this.playNow_Digits.removeEventListener(AdminResultActivity.this._playNow_Digits_child_listener);
									AdminResultActivity.this.playNow_Digits = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.path_playNow2);
									AdminResultActivity.this.playNow_Digits.addChildEventListener(AdminResultActivity.this._playNow_Digits_child_listener);
									AdminResultActivity.this.playNow_Digits.addListenerForSingleValueEvent(new ValueEventListener() {
										public void onCancelled(DatabaseError param4DatabaseError) {}

										public void onDataChange(DataSnapshot param4DataSnapshot) {
											listmap_playnow_d = new ArrayList();
											try {
												GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

												};
												Iterator<DataSnapshot> iterator = param4DataSnapshot.getChildren().iterator();
												while (iterator.hasNext()) {
													HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
													AdminResultActivity.this.listmap_playnow_d.add(hashMap);
												}
											} catch (Exception exception) {
												exception.printStackTrace();
											}
											if (AdminResultActivity.this.listmap_playnow_d.size() == 0) {
												AdminResultActivity.this.l_win.setVisibility(View.GONE);
											} else {
												AdminResultActivity.this.l_win.setVisibility(View.VISIBLE);
											}
											AdminResultActivity.this.listview1.setAdapter((ListAdapter)new AdminResultActivity.Listview1Adapter(AdminResultActivity.this.listmap_playnow_d));
											((BaseAdapter)AdminResultActivity.this.listview1.getAdapter()).notifyDataSetChanged();
											AdminResultActivity.this.pd.dismiss();
										}
									});
								}
							});
						}
					};
					AdminResultActivity.this._timer.schedule(AdminResultActivity.this.t2, 3000);
				}
			}
		});
		this.button_win.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.AdminResultActivity.AnonymousClass4 */

			public void onClick(View _view) {
				if ((AdminResultActivity.this.win_value.length() == 3 && AdminResultActivity.this.game_sp.equals("Patti")) || (AdminResultActivity.this.win_value.length() == 1 && AdminResultActivity.this.game_sp.equals("Single"))) {
					final String win = AdminResultActivity.this.win_value.getText().toString();
					AdminResultActivity.this.win_n = Double.parseDouble(win);
					AdminResultActivity.this.win_r_n = 0.0d;
					AdminResultActivity.this.pd.show();
					AdminResultActivity.this.c = Calendar.getInstance();
					AdminResultActivity.this.map_r = new HashMap();
					AdminResultActivity.this.map_r.put("game", AdminResultActivity.this.getIntent().getStringExtra("Game").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")));
					AdminResultActivity.this.map_r.put("digit", AdminResultActivity.this.win_value.getText().toString());
					AdminResultActivity.this.map_r.put("date", new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(AdminResultActivity.this.c.getTime()));
					AdminResultActivity.this.map_r.put("bazi", AdminResultActivity.this.bazi_name);
					AdminResultActivity.this.ff_results.push().updateChildren(AdminResultActivity.this.map_r);
					AdminResultActivity.this.map_r.clear();
					AdminResultActivity.this.complete = "yes";
					AdminResultActivity.this.win_r_n = 0.0d;
					AdminResultActivity.this.tttt = new TimerTask() {
						/* class dream.fata.fat.AdminResultActivity.AnonymousClass4.AnonymousClass1 */

						public void run() {
							AdminResultActivity.this.runOnUiThread(new Runnable() {
								/* class dream.fata.fat.AdminResultActivity.AnonymousClass4.AnonymousClass1.AnonymousClass1 */

								public void run() {
									if (!AdminResultActivity.this.complete.equals("no")) {
										AdminResultActivity.this.complete = "no";
										if (((HashMap) AdminResultActivity.this.listmap_playNow.get((int) AdminResultActivity.this.win_r_n)).get("digit").toString().equals(win)) {
											AdminResultActivity.this.winrs = ((HashMap) AdminResultActivity.this.listmap_playNow.get((int) AdminResultActivity.this.win_r_n)).get("rs").toString();
											AdminResultActivity.this.WinEmail = ((HashMap) AdminResultActivity.this.listmap_playNow.get((int) AdminResultActivity.this.win_r_n)).get("email").toString().replace(".", "");
											AdminResultActivity.this.pathWinner = "users/".concat(AdminResultActivity.this.WinEmail);
											AdminResultActivity.this.users.removeEventListener(AdminResultActivity.this._users_child_listener);
											AdminResultActivity.this.users = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.pathWinner);
											AdminResultActivity.this.users.addChildEventListener(AdminResultActivity.this._users_child_listener);
											AdminResultActivity.this.users.addListenerForSingleValueEvent(new ValueEventListener() {
												public void onCancelled(DatabaseError param4DatabaseError) {}

												public void onDataChange(DataSnapshot param4DataSnapshot) {
													listmap_users = new ArrayList();
													try {
														GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

														};
														Iterator<DataSnapshot> iterator = param4DataSnapshot.getChildren().iterator();
														while (iterator.hasNext()) {
															HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
															AdminResultActivity.this.listmap_users.add(hashMap);
														}
													} catch (Exception exception) {
														exception.printStackTrace();
													}
													AdminResultActivity.this.OldBal = ((HashMap) AdminResultActivity.this.listmap_users.get(0)).get("balance").toString();
													AdminResultActivity.this.map_r.clear();
													AdminResultActivity.this.map_r = new HashMap();
													if (AdminResultActivity.this.game_sp.equals("Patti")) {
														AdminResultActivity.this.map_r.put("balance", String.valueOf((long) (Double.parseDouble(AdminResultActivity.this.OldBal) + (Double.parseDouble(AdminResultActivity.this.winrs) * 100.0d))));
													} else {
														AdminResultActivity.this.map_r.put("balance", String.valueOf((long) (Double.parseDouble(AdminResultActivity.this.OldBal) + (Double.parseDouble(AdminResultActivity.this.winrs) * 9.0d))));
													}
													AdminResultActivity.this.users.child(AdminResultActivity.this.WinEmail).updateChildren(AdminResultActivity.this.map_r);
													AdminResultActivity.this.user_h.child(AdminResultActivity.this.WinEmail).updateChildren(AdminResultActivity.this.map_r);
													AdminResultActivity.this.map_r.clear();
													AdminResultActivity.this.path_playNow2 = "userHistory/".concat(AdminResultActivity.this.WinEmail).concat("/Games/").concat(AdminResultActivity.this.getIntent().getStringExtra("Game")).concat("_").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp"));
													AdminResultActivity.this.user_logGames.removeEventListener(AdminResultActivity.this._user_logGames_child_listener);
													AdminResultActivity.this.user_logGames = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.path_playNow2);
													AdminResultActivity.this.user_logGames.addChildEventListener(AdminResultActivity.this._user_logGames_child_listener);
													AdminResultActivity.this.map_r = new HashMap();
													AdminResultActivity.this.map_r.put("bazi", AdminResultActivity.this.bazi_name);
													AdminResultActivity.this.map_r.put("digit", win);
													if (AdminResultActivity.this.game_sp.equals("Patti")) {
														AdminResultActivity.this.map_r.put("rs", AdminResultActivity.this.winrs.concat(" =(").concat(String.valueOf(Double.parseDouble(AdminResultActivity.this.winrs) * 100.0d).concat(")")));
													} else {
														AdminResultActivity.this.map_r.put("rs", AdminResultActivity.this.winrs.concat(" =(").concat(String.valueOf(Double.parseDouble(AdminResultActivity.this.winrs) * 9.0d).concat(")")));
													}
													AdminResultActivity.this.map_r.put(NotificationCompat.CATEGORY_STATUS, "Won");
													AdminResultActivity.this.map_r.put("time", new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(AdminResultActivity.this.c.getTime()));
													AdminResultActivity.this.user_logGames.push().updateChildren(AdminResultActivity.this.map_r);
													AdminResultActivity.this.map_r.clear();
													if (AdminResultActivity.this.win_r_n == ((double) AdminResultActivity.this.listmap_playNow.size()) || AdminResultActivity.this.win_r_n > ((double) AdminResultActivity.this.listmap_playNow.size())) {

														AdminResultActivity.this.textview1.setText(AdminResultActivity.this.game.concat(" ").concat(AdminResultActivity.this.game_sp).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
														AdminResultActivity.this.win_value.setText("");
														AdminResultActivity.this.complete = "no";
														AdminResultActivity.this.tttt.cancel();
														AdminResultActivity.this.play_now.child("").removeValue();
														AdminResultActivity.this.playNow_Digits.child("").removeValue();
														lllt = new TimerTask() {
															@Override
															public void run() {
																runOnUiThread(new Runnable() {
																				  @Override
																				  public void run() {
														AdminResultActivity.this.pd.dismiss();
														AdminResultActivity.this.finish();
														SketchwareUtil.showMessage(AdminResultActivity.this.getApplicationContext(), "Completed");

																					  return;
																				  }
																});
															}
														};
														_timer.schedule(lllt, (int)(10000));													}
													AdminResultActivity.this.textview1.setText("(".concat(String.valueOf((long) AdminResultActivity.this.win_r_n)).concat("/").concat(String.valueOf((long) AdminResultActivity.this.listmap_playNow.size())).concat(")"));
													AdminResultActivity.this.complete = "yes";
												}

												//@Override // com.google.firebase.database.ValueEventListener
												//public void onCancelled(DatabaseError _databaseError) {
												//}
											});
										} else {
											final String win = ((HashMap) AdminResultActivity.this.listmap_playNow.get((int) AdminResultActivity.this.win_r_n)).get("digit").toString();
											AdminResultActivity.this.winrs = ((HashMap) AdminResultActivity.this.listmap_playNow.get((int) AdminResultActivity.this.win_r_n)).get("rs").toString();
											AdminResultActivity.this.WinEmail = ((HashMap) AdminResultActivity.this.listmap_playNow.get((int) AdminResultActivity.this.win_r_n)).get("email").toString().replace(".", "");
											AdminResultActivity.this.pathWinner = "users/".concat(AdminResultActivity.this.WinEmail);
											AdminResultActivity.this.users.removeEventListener(AdminResultActivity.this._users_child_listener);
											AdminResultActivity.this.users = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.pathWinner);
											AdminResultActivity.this.users.addChildEventListener(AdminResultActivity.this._users_child_listener);
											AdminResultActivity.this.users.addListenerForSingleValueEvent(new ValueEventListener() {
												//public void onCancelled(DatabaseError param4DatabaseError) {}

												public void onDataChange(DataSnapshot param4DataSnapshot) {
													listmap_users = new ArrayList();
													try {
														GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

														};
														Iterator<DataSnapshot> iterator = param4DataSnapshot.getChildren().iterator();
														while (iterator.hasNext()) {
															HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
															AdminResultActivity.this.listmap_users.add(hashMap);
														}
													} catch (Exception exception) {
														exception.printStackTrace();
													}
													AdminResultActivity.this.OldBal = ((HashMap) AdminResultActivity.this.listmap_users.get(0)).get("balance").toString();
													AdminResultActivity.this.map_r.clear();
													AdminResultActivity.this.map_r = new HashMap();
													AdminResultActivity.this.map_r.put("balance", String.valueOf((long) (Double.parseDouble(AdminResultActivity.this.OldBal) + (Double.parseDouble(AdminResultActivity.this.winrs) * 0.0d))));
													AdminResultActivity.this.users.child(AdminResultActivity.this.WinEmail).updateChildren(AdminResultActivity.this.map_r);
													AdminResultActivity.this.user_h.child(AdminResultActivity.this.WinEmail).updateChildren(AdminResultActivity.this.map_r);
													AdminResultActivity.this.map_r.clear();
													AdminResultActivity.this.path_playNow2 = "userHistory/".concat(AdminResultActivity.this.WinEmail).concat("/Games/").concat(AdminResultActivity.this.getIntent().getStringExtra("Game")).concat("_").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp"));
													AdminResultActivity.this.user_logGames.removeEventListener(AdminResultActivity.this._user_logGames_child_listener);
													AdminResultActivity.this.user_logGames = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.path_playNow2);
													AdminResultActivity.this.user_logGames.addChildEventListener(AdminResultActivity.this._user_logGames_child_listener);
													AdminResultActivity.this.map_r = new HashMap();
													AdminResultActivity.this.map_r.put("bazi", AdminResultActivity.this.bazi_name);
													AdminResultActivity.this.map_r.put("digit", win);
													AdminResultActivity.this.map_r.put("rs", AdminResultActivity.this.winrs);
													AdminResultActivity.this.map_r.put(NotificationCompat.CATEGORY_STATUS, "Loss");
													AdminResultActivity.this.map_r.put("time", new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(AdminResultActivity.this.c.getTime()));
													AdminResultActivity.this.user_logGames.push().updateChildren(AdminResultActivity.this.map_r);
													AdminResultActivity.this.map_r.clear();
													if (AdminResultActivity.this.win_r_n == ((double) AdminResultActivity.this.listmap_playNow.size()) || AdminResultActivity.this.win_r_n > ((double) AdminResultActivity.this.listmap_playNow.size())) {
														AdminResultActivity.this.textview1.setText(AdminResultActivity.this.game.concat(" ").concat(AdminResultActivity.this.game_sp).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
														AdminResultActivity.this.win_value.setText("");

														AdminResultActivity.this.complete = "no";
														AdminResultActivity.this.tttt.cancel();
														AdminResultActivity.this.play_now.child("").removeValue();
														AdminResultActivity.this.playNow_Digits.child("").removeValue();

														lllt = new TimerTask() {
															@Override
															public void run() {
																runOnUiThread(new Runnable() {
																	@Override
																	public void run() {
																		SketchwareUtil.showMessage(AdminResultActivity.this.getApplicationContext(), "Completed");
																		AdminResultActivity.this.pd.dismiss();
														AdminResultActivity.this.finish();
														return;
													}
												});
											}
										};
														_timer.schedule(lllt, (int)(10000));													}
													AdminResultActivity.this.textview1.setText("(".concat(String.valueOf((long) AdminResultActivity.this.win_r_n)).concat("/").concat(String.valueOf((long) AdminResultActivity.this.listmap_playNow.size())).concat(")"));
													AdminResultActivity.this.complete = "yes";
												}

												@Override // com.google.firebase.database.ValueEventListener
												public void onCancelled(DatabaseError _databaseError) {
												}
											});
										}
									}
									AdminResultActivity.access$2308(AdminResultActivity.this);
								}
							});
						}
					};
					AdminResultActivity.this._timer.scheduleAtFixedRate(AdminResultActivity.this.tttt, 200, 1000);
				}
			}
		});
		this.textview1.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.AdminResultActivity.AnonymousClass5 */

			public void onClick(View _view) {
				AdminResultActivity.this.copy = "";
				AdminResultActivity.this.ccopyval2 = "";
				AdminResultActivity.this.copyval1 = "";
				for (int _repeat29 = 0; _repeat29 < AdminResultActivity.this.listmap_playnow_d.size(); _repeat29++) {
					AdminResultActivity adminResultActivity = AdminResultActivity.this;
					adminResultActivity.copyval1 = ((HashMap) adminResultActivity.listmap_playnow_d.get(_repeat29)).get("digit").toString();
					AdminResultActivity.this.n_f = 0.0d;
					for (int _repeat41 = 0; _repeat41 < AdminResultActivity.this.listmap_f.size(); _repeat41++) {
						if (((HashMap) AdminResultActivity.this.listmap_f.get((int) AdminResultActivity.this.n_f)).containsKey(AdminResultActivity.this.copyval1)) {
							AdminResultActivity adminResultActivity2 = AdminResultActivity.this;
							adminResultActivity2.ccopyval2 = ((HashMap) adminResultActivity2.listmap_f.get((int) AdminResultActivity.this.n_f)).get(AdminResultActivity.this.copyval1).toString();
						}
						AdminResultActivity.access$4108(AdminResultActivity.this);
					}
					AdminResultActivity adminResultActivity3 = AdminResultActivity.this;
					adminResultActivity3.copy = adminResultActivity3.copy.concat(AdminResultActivity.this.copyval1).concat("------").concat(AdminResultActivity.this.ccopyval2).concat("\n");
					if (_repeat29 + 1 == AdminResultActivity.this.listmap_playnow_d.size()) {
						SketchwareUtil.showMessage(AdminResultActivity.this.getApplicationContext(), "Copied");
						AdminResultActivity adminResultActivity4 = AdminResultActivity.this;
						adminResultActivity4.getApplicationContext();
						((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", copy));
					}
				}
			}
		});
		ChildEventListener childEventListener = new ChildEventListener() {
			public void onCancelled(DatabaseError param1DatabaseError) {
				param1DatabaseError.getCode();
				param1DatabaseError.getMessage();
			}

			public void onChildAdded(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildChanged(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildMoved(DataSnapshot param1DataSnapshot, String param1String) {}

			public void onChildRemoved(DataSnapshot param1DataSnapshot) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}
		};
		this._play_now_child_listener = childEventListener;
		this.play_now.addChildEventListener(childEventListener);
		childEventListener = new ChildEventListener() {
			public void onCancelled(DatabaseError param1DatabaseError) {
				param1DatabaseError.getCode();
				param1DatabaseError.getMessage();
			}

			public void onChildAdded(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildChanged(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildMoved(DataSnapshot param1DataSnapshot, String param1String) {}

			public void onChildRemoved(DataSnapshot param1DataSnapshot) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}
		};
		this._playNow_Digits_child_listener = childEventListener;
		this.playNow_Digits.addChildEventListener(childEventListener);
		childEventListener = new ChildEventListener() {
			public void onCancelled(DatabaseError param1DatabaseError) {
				param1DatabaseError.getCode();
				param1DatabaseError.getMessage();
			}

			public void onChildAdded(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildChanged(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildMoved(DataSnapshot param1DataSnapshot, String param1String) {}

			public void onChildRemoved(DataSnapshot param1DataSnapshot) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}
		};
		this._users_child_listener = childEventListener;
		this.users.addChildEventListener(childEventListener);
		childEventListener = new ChildEventListener() {
			public void onCancelled(DatabaseError param1DatabaseError) {
				param1DatabaseError.getCode();
				param1DatabaseError.getMessage();
			}

			public void onChildAdded(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildChanged(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildMoved(DataSnapshot param1DataSnapshot, String param1String) {}

			public void onChildRemoved(DataSnapshot param1DataSnapshot) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}
		};
		this._user_h_child_listener = childEventListener;
		this.user_h.addChildEventListener(childEventListener);


		childEventListener = new ChildEventListener() {
			public void onCancelled(DatabaseError param1DatabaseError) {
				param1DatabaseError.getCode();
				param1DatabaseError.getMessage();
			}

			public void onChildAdded(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildChanged(DataSnapshot param1DataSnapshot, String param1String) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}

			public void onChildMoved(DataSnapshot param1DataSnapshot, String param1String) {}

			public void onChildRemoved(DataSnapshot param1DataSnapshot) {
				GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

				};
				param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
			}
		};
		this._user_logGames_child_listener = childEventListener;
		this.user_logGames.addChildEventListener(childEventListener);

	}

	private void initializeLogic() {
		ProgressDialog progressDialog = new ProgressDialog(this);
		this.pd = progressDialog;
		progressDialog.setMessage("Loading");
		this.pd.setCancelable(false);
		this.pd.show();
		this.game = getIntent().getStringExtra("Game");
		this.game_sp = getIntent().getStringExtra("Game_sp");
		this.bazi_number = 1.0d;
		this.bazi_name = String.valueOf((long) 1.0d).concat("BAZI");
		this.textview1.setText(getIntent().getStringExtra("Game").concat(" ").concat(getIntent().getStringExtra("Game_sp")).concat(" [").concat(this.bazi_name).concat("]"));
		if (getIntent().getStringExtra("Game").equals("Fatafat")) {
			TimerTask r0 = new TimerTask() {
				/* class dream.fata.fat.AdminResultActivity.AnonymousClass11 */

				public void run() {
					AdminResultActivity.this.runOnUiThread(new Runnable() {
						/* class dream.fata.fat.AdminResultActivity.AnonymousClass11.AnonymousClass1 */

						public void run() {
							AdminResultActivity.this.c = Calendar.getInstance();
							AdminResultActivity.this.current_time = (Double.parseDouble(new SimpleDateFormat("HH").format(AdminResultActivity.this.c.getTime())) * 60.0d) + Double.parseDouble(new SimpleDateFormat("mm").format(AdminResultActivity.this.c.getTime()));
							if (600.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "1BAZI";
								AdminResultActivity.this.bazi_number = 1.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (690.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "2BAZI";
								AdminResultActivity.this.bazi_number = 2.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (780.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "3BAZI";
								AdminResultActivity.this.bazi_number = 3.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (870.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_number = 4.0d;
								AdminResultActivity.this.bazi_name = "4BAZI";
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (960.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_number = 5.0d;
								AdminResultActivity.this.bazi_name = "5BAZI";
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (1050.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_number = 6.0d;
								AdminResultActivity.this.bazi_name = "6BAZI";
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (420.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "7BAZI";
								AdminResultActivity.this.bazi_number = 7.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (1230.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "8BAZI";
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
								AdminResultActivity.this.bazi_number = 8.0d;
							} else {
								AdminResultActivity.this.bazi_name = "GAME OVER";
								AdminResultActivity.this.bazi_number = 8.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							}
						}
					});
				}
			};
			this.t = r0;
			this._timer.schedule(r0, 1000);
		} else if (getIntent().getStringExtra("Game").equals("SILIGURI")) {
			TimerTask r02 = new TimerTask() {
				/* class dream.fata.fat.AdminResultActivity.AnonymousClass12 */

				public void run() {
					AdminResultActivity.this.runOnUiThread(new Runnable() {
						/* class dream.fata.fat.AdminResultActivity.AnonymousClass12.AnonymousClass1 */

						public void run() {
							AdminResultActivity.this.c = Calendar.getInstance();
							AdminResultActivity.this.current_time = (Double.parseDouble(new SimpleDateFormat("HH").format(AdminResultActivity.this.c.getTime())) * 60.0d) + Double.parseDouble(new SimpleDateFormat("mm").format(AdminResultActivity.this.c.getTime()));
							if (600.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "1BAZI";
								AdminResultActivity.this.bazi_number = 1.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (690.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "2BAZI";
								AdminResultActivity.this.bazi_number = 2.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (780.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "3BAZI";
								AdminResultActivity.this.bazi_number = 3.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (870.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_number = 4.0d;
								AdminResultActivity.this.bazi_name = "4BAZI";
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (960.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_number = 5.0d;
								AdminResultActivity.this.bazi_name = "5BAZI";
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (1050.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_number = 6.0d;
								AdminResultActivity.this.bazi_name = "6BAZI";
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (420.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "7BAZI";
								AdminResultActivity.this.bazi_number = 7.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (1230.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "8BAZI";
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
								AdminResultActivity.this.bazi_number = 8.0d;
							} else {
								AdminResultActivity.this.bazi_name = "GAME OVER";
								AdminResultActivity.this.bazi_number = 8.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							}
						}
					});
				}
			};
			this.t = r02;
			this._timer.schedule(r02, 1000);
		} else if (getIntent().getStringExtra("Game").equals("Main Bazar")) {
			TimerTask r03 = new TimerTask() {
				/* class dream.fata.fat.AdminResultActivity.AnonymousClass13 */

				public void run() {
					AdminResultActivity.this.runOnUiThread(new Runnable() {
						/* class dream.fata.fat.AdminResultActivity.AnonymousClass13.AnonymousClass1 */

						public void run() {
							AdminResultActivity.this.c = Calendar.getInstance();
							AdminResultActivity.this.current_time = (Double.parseDouble(new SimpleDateFormat("HH").format(AdminResultActivity.this.c.getTime())) * 60.0d) + Double.parseDouble(new SimpleDateFormat("mm").format(AdminResultActivity.this.c.getTime()));
							if (1345.0d > AdminResultActivity.this.current_time && AdminResultActivity.this.current_time > 121.0d) {
								AdminResultActivity.this.bazi_name = "OPEN";
								AdminResultActivity.this.bazi_number = 1.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else if (1480.0d > AdminResultActivity.this.current_time) {
								AdminResultActivity.this.bazi_name = "CLOSE";
								AdminResultActivity.this.bazi_number = 2.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							} else {
								AdminResultActivity.this.bazi_name = "GAME OVER";
								AdminResultActivity.this.bazi_number = 2.0d;
								AdminResultActivity.this.textview1.setText(AdminResultActivity.this.getIntent().getStringExtra("Game").concat(" ").concat(AdminResultActivity.this.getIntent().getStringExtra("Game_sp")).concat(" [").concat(AdminResultActivity.this.bazi_name).concat("]"));
							}
						}
					});
				}
			};
			this.t = r03;
			this._timer.schedule(r03, 1000);
		}
		TimerTask r04 = new TimerTask() {
			/* class dream.fata.fat.AdminResultActivity.AnonymousClass14 */

			public void run() {
				AdminResultActivity.this.runOnUiThread(new Runnable() {
					/* class dream.fata.fat.AdminResultActivity.AnonymousClass14.AnonymousClass1 */

					public void run() {
						AdminResultActivity.this.c.setTimeInMillis(AdminResultActivity.this.c.getTimeInMillis() - 7200000);
						AdminResultActivity.this.path_playNow = "playNow_".concat(AdminResultActivity.this.game_sp.toLowerCase().concat("/".concat(AdminResultActivity.this.game.concat("/".concat(new SimpleDateFormat("dd-MM-yyyy").format(AdminResultActivity.this.c.getTime()).concat("/".concat(AdminResultActivity.this.bazi_name)))))));
						AdminResultActivity.this.c = Calendar.getInstance();
						AdminResultActivity.this.play_now.removeEventListener(AdminResultActivity.this._play_now_child_listener);
						AdminResultActivity.this.play_now = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.path_playNow);
						AdminResultActivity.this.play_now.addChildEventListener(AdminResultActivity.this._play_now_child_listener);
						AdminResultActivity.this.play_now.addListenerForSingleValueEvent(new ValueEventListener() {
							public void onCancelled(DatabaseError param3DatabaseError) {}

							public void onDataChange(DataSnapshot param3DataSnapshot) {
								listmap_playNow = new ArrayList();
								try {
									GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

									};
									Iterator<DataSnapshot> iterator = param3DataSnapshot.getChildren().iterator();
									while (iterator.hasNext()) {
										HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
										AdminResultActivity.this.listmap_playNow.add(hashMap);
									}
									AdminResultActivity.this.CalculateRS(AdminResultActivity.this.listmap_playNow);
									return;
								} catch (Exception exception) {
									exception.printStackTrace();
									return;
								}
							}
						});
						AdminResultActivity.this.c.setTimeInMillis(AdminResultActivity.this.c.getTimeInMillis() - 7200000);
						AdminResultActivity.this.path_playNow2 = "playNow_".concat(AdminResultActivity.this.game_sp.toLowerCase().concat("_Digits/".concat(AdminResultActivity.this.game.concat("/".concat(new SimpleDateFormat("dd-MM-yyyy").format(AdminResultActivity.this.c.getTime()).concat("/".concat(AdminResultActivity.this.bazi_name)))))));
						AdminResultActivity.this.c = Calendar.getInstance();
						AdminResultActivity.this.playNow_Digits.removeEventListener(AdminResultActivity.this._playNow_Digits_child_listener);
						AdminResultActivity.this.playNow_Digits = AdminResultActivity.this._firebase.getReference(AdminResultActivity.this.path_playNow2);
						AdminResultActivity.this.playNow_Digits.addChildEventListener(AdminResultActivity.this._playNow_Digits_child_listener);
						AdminResultActivity.this.playNow_Digits.addListenerForSingleValueEvent(new ValueEventListener() {
							public void onCancelled(DatabaseError param3DatabaseError) {}

							public void onDataChange(DataSnapshot param3DataSnapshot) {
								listmap_playnow_d = new ArrayList();
								try {
									GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

									};
									Iterator<DataSnapshot> iterator = param3DataSnapshot.getChildren().iterator();
									while (iterator.hasNext()) {
										HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
										AdminResultActivity.this.listmap_playnow_d.add(hashMap);
									}
								} catch (Exception exception) {
									exception.printStackTrace();
								}
								if (AdminResultActivity.this.listmap_playnow_d.size() == 0) {
									AdminResultActivity.this.l_win.setVisibility(View.GONE);
								} else {
									AdminResultActivity.this.l_win.setVisibility(View.VISIBLE);
								}
								AdminResultActivity.this.listview1.setAdapter((ListAdapter)new AdminResultActivity.Listview1Adapter(AdminResultActivity.this.listmap_playnow_d));
								((BaseAdapter)AdminResultActivity.this.listview1.getAdapter()).notifyDataSetChanged();
								AdminResultActivity.this.pd.dismiss();
							}
						});
					}
				});
			}
		};
		this.t2 = r04;
		this._timer.schedule(r04, 3000);
	}

	/* access modifiers changed from: private */
	/* access modifiers changed from: public */
	private void CalculateRS(ArrayList<HashMap<String, Object>> listmap2) {
		double totalForAllUniqueRs;
		double totalForAllUniqueRs2 = 0.0d;
		this.sample_List = new HashMap<>();
		for (int i = 0; i < listmap2.size(); i++) {
			HashMap temp2 = listmap2.get(i);
			if (temp2 == null) {
				totalForAllUniqueRs = totalForAllUniqueRs2;
			} else if (!temp2.containsKey("digit") || !temp2.containsKey("rs")) {
				totalForAllUniqueRs = totalForAllUniqueRs2;
			} else {
				double digit = Double.parseDouble(temp2.get("digit").toString());
				double rs2 = Double.parseDouble(temp2.get("rs").toString());
				if (!this.sample_List.containsKey(Double.valueOf(digit))) {
					this.sample_List.put(Double.valueOf(digit), Double.valueOf(rs2));
					totalForAllUniqueRs2 += rs2;
					HashMap<String, Object> _item = new HashMap<>();
					_item.put(String.valueOf((long) digit), String.valueOf((long) this.sample_List.get(Double.valueOf(digit)).doubleValue()));
					this.listmap_f.add(_item);
					TimerTask r10 = new TimerTask() {
						/* class dream.fata.fat.AdminResultActivity.AnonymousClass15 */

						public void run() {
							AdminResultActivity.this.runOnUiThread(new Runnable() {
								/* class dream.fata.fat.AdminResultActivity.AnonymousClass15.AnonymousClass1 */

								public void run() {
									AdminResultActivity.this.listview1.setAdapter((ListAdapter) new Listview1Adapter(AdminResultActivity.this.listmap_playnow_d));
									((BaseAdapter) AdminResultActivity.this.listview1.getAdapter()).notifyDataSetChanged();
								}
							});
						}
					};
					this.ttt = r10;
					this._timer.schedule(r10, 2000);
				} else {
					double odlVal = this.sample_List.get(Double.valueOf(digit)).doubleValue();
					this.sample_List.remove(Double.valueOf(digit));
					this.sample_List.put(Double.valueOf(digit), Double.valueOf(rs2 + odlVal));
					HashMap<String, Object> _item2 = new HashMap<>();
					totalForAllUniqueRs = totalForAllUniqueRs2;
					_item2.put(String.valueOf((long) digit), String.valueOf((long) this.sample_List.get(Double.valueOf(digit)).doubleValue()));
					this.listmap_f.add(_item2);
					TimerTask r1 = new TimerTask() {
						/* class dream.fata.fat.AdminResultActivity.AnonymousClass16 */

						public void run() {
							AdminResultActivity.this.runOnUiThread(new Runnable() {
								/* class dream.fata.fat.AdminResultActivity.AnonymousClass16.AnonymousClass1 */

								public void run() {
									AdminResultActivity.this.listview1.setAdapter((ListAdapter) new Listview1Adapter(AdminResultActivity.this.listmap_playnow_d));
									((BaseAdapter) AdminResultActivity.this.listview1.getAdapter()).notifyDataSetChanged();
								}
							});
						}
					};
					this.ttt = r1;
					this._timer.schedule(r1, 2000);
				}
			}
			totalForAllUniqueRs = 0;
			totalForAllUniqueRs2 = totalForAllUniqueRs;
		}
	}

	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;

		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			this._data = _arr;
		}

		public int getCount() {
			return this._data.size();
		}

		public HashMap<String, Object> getItem(int _index) {
			return this._data.get(_index);
		}

		public long getItemId(int _index) {
			return (long) _index;
		}

		public View getView(int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) AdminResultActivity.this.getBaseContext().getSystemService("layout_inflater");
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.admin_result_, (ViewGroup) null);
			}
			LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			TextView textView = (TextView) _view.findViewById(R.id.textview3);
			TextView textview_rs = (TextView) _view.findViewById(R.id.textview_rs);
			Button button1 = (Button) _view.findViewById(R.id.button1);
			linear1.setElevation(20.0f);
			linear1.setBackground(new GradientDrawable() {
				/* class dream.fata.fat.AdminResultActivity.Listview1Adapter.AnonymousClass1 */

				public GradientDrawable getIns(int a, int b, int c, int d) {
					setCornerRadius((float) a);
					setStroke(b, c);
					setColor(d);
					return this;
				}
			}.getIns(20, 1, -10453621, -1));
			((TextView) _view.findViewById(R.id.textview_digit)).setText(((HashMap) AdminResultActivity.this.listmap_playnow_d.get(_position)).get("digit").toString());
			AdminResultActivity.this.n_f = 0.0d;
			for (int _repeat41 = 0; _repeat41 < AdminResultActivity.this.listmap_f.size(); _repeat41++) {
				if (((HashMap) AdminResultActivity.this.listmap_f.get((int) AdminResultActivity.this.n_f)).containsKey(((HashMap) AdminResultActivity.this.listmap_playnow_d.get(_position)).get("digit").toString())) {
					textview_rs.setText(((HashMap) AdminResultActivity.this.listmap_f.get((int) AdminResultActivity.this.n_f)).get(((HashMap) AdminResultActivity.this.listmap_playnow_d.get(_position)).get("digit").toString()).toString());
				}
				AdminResultActivity.access$4108(AdminResultActivity.this);
			}
			button1.setOnClickListener(new View.OnClickListener() {
				/* class dream.fata.fat.AdminResultActivity.Listview1Adapter.AnonymousClass2 */

				public void onClick(View _view) {
				}
			});
			return _view;
		}
	}

	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	@Deprecated
	public int getLocationX(View _v) {
		int[] _location = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}

	@Deprecated
	public int getLocationY(View _v) {
		int[] _location = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}

	@Deprecated
	public int getRandom(int _min, int _max) {
		return new Random().nextInt((_max - _min) + 1) + _min;
	}

	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx)) {
				_result.add(Double.valueOf((double) _arr.keyAt(_iIdx)));
			}
		}
		return _result;
	}

	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(1, (float) _input, getResources().getDisplayMetrics());
	}

	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}

	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
