package dream.fata.fat;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class UserDetailsActivity extends AppCompatActivity {
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private Timer _timer = new Timer();
	private Toolbar _toolbar;
	private ChildEventListener _user_h_child_listener;
	private ChildEventListener _user_noti_child_listener;
	private ChildEventListener _users_child_listener;
	private double balance = 0.0d;
	private Button button1;
	private Button button2;
	private Calendar c = Calendar.getInstance();
	private Intent i = new Intent();
	private ImageView imageview1;
	private LinearLayout linear1;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private LinearLayout linear13;
	private LinearLayout linear14;
	private LinearLayout linear15;
	private LinearLayout linear16;
	private LinearLayout linear17;
	private LinearLayout linear18;
	private LinearLayout linear19;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> map2 = new HashMap<>();
	private String path = "";
	private String path_noti = "";
	private ProgressDialog pd;
	private TimerTask t;
	private String test = "";
	private TextView textview10;
	private TextView textview13;
	private TextView textview15;
	private TextView textview16;
	private TextView textview19;
	private TextView textview2;
	private TextView textview20;
	private TextView textview22;
	private TextView textview24;
	private TextView textview4;
	private TextView textview6;
	private TextView textview8;
	private TextView textview_ammo;
	private TextView textview_bal;
	private TextView textview_email;
	private TextView textview_ifsc;
	private TextView textview_name;
	private TextView textview_number;
	private TextView textview_pending_notice;
	private TextView textview_ref;
	private TextView textview_w_d;
	private TextView textview_w_m;
	private TextView textview_wallet;
	private TextView textview_wnumber;
	private DatabaseReference user_h = this._firebase.getReference("user_h");
	private DatabaseReference user_noti = this._firebase.getReference("user_noti");
	private DatabaseReference users = this._firebase.getReference("users");
	private ScrollView vscroll1;

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.user_details);
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
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass1 */

			public void onClick(View _v) {
				UserDetailsActivity.this.onBackPressed();
			}
		});
		this.vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		this.linear11 = (LinearLayout) findViewById(R.id.linear11);
		this.linear1 = (LinearLayout) findViewById(R.id.linear1);
		this.linear8 = (LinearLayout) findViewById(R.id.linear8);
		this.linear2 = (LinearLayout) findViewById(R.id.linear2);
		this.linear4 = (LinearLayout) findViewById(R.id.linear4);
		this.linear5 = (LinearLayout) findViewById(R.id.linear5);
		this.linear6 = (LinearLayout) findViewById(R.id.linear6);
		this.linear7 = (LinearLayout) findViewById(R.id.linear7);
		this.linear17 = (LinearLayout) findViewById(R.id.linear17);
		this.linear18 = (LinearLayout) findViewById(R.id.linear18);
		this.linear19 = (LinearLayout) findViewById(R.id.linear19);
		this.linear12 = (LinearLayout) findViewById(R.id.linear12);
		this.linear14 = (LinearLayout) findViewById(R.id.linear14);
		this.textview_pending_notice = (TextView) findViewById(R.id.textview_pending_notice);
		this.textview2 = (TextView) findViewById(R.id.textview2);
		this.textview_name = (TextView) findViewById(R.id.textview_name);
		this.textview4 = (TextView) findViewById(R.id.textview4);
		this.textview_email = (TextView) findViewById(R.id.textview_email);
		this.textview6 = (TextView) findViewById(R.id.textview6);
		this.textview_number = (TextView) findViewById(R.id.textview_number);
		this.textview8 = (TextView) findViewById(R.id.textview8);
		this.textview_w_m = (TextView) findViewById(R.id.textview_w_m);
		this.textview10 = (TextView) findViewById(R.id.textview10);
		this.textview_w_d = (TextView) findViewById(R.id.textview_w_d);
		this.textview20 = (TextView) findViewById(R.id.textview20);
		this.textview_ifsc = (TextView) findViewById(R.id.textview_ifsc);
		this.textview22 = (TextView) findViewById(R.id.textview22);
		this.textview_wallet = (TextView) findViewById(R.id.textview_wallet);
		this.textview24 = (TextView) findViewById(R.id.textview24);
		this.textview_wnumber = (TextView) findViewById(R.id.textview_wnumber);
		this.textview15 = (TextView) findViewById(R.id.textview15);
		this.textview_bal = (TextView) findViewById(R.id.textview_bal);
		this.linear13 = (LinearLayout) findViewById(R.id.linear13);
		this.linear15 = (LinearLayout) findViewById(R.id.linear15);
		this.linear16 = (LinearLayout) findViewById(R.id.linear16);
		this.textview13 = (TextView) findViewById(R.id.textview13);
		this.textview_ammo = (TextView) findViewById(R.id.textview_ammo);
		this.textview16 = (TextView) findViewById(R.id.textview16);
		this.textview_ref = (TextView) findViewById(R.id.textview_ref);
		this.textview19 = (TextView) findViewById(R.id.textview19);
		this.imageview1 = (ImageView) findViewById(R.id.imageview1);
		this.button1 = (Button) findViewById(R.id.button1);
		this.button2 = (Button) findViewById(R.id.button2);
		this.imageview1.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass2 */

			public void onClick(View _view) {
				UserDetailsActivity.this.i.setClass(UserDetailsActivity.this.getApplicationContext(), ImgFullActivity.class);
				UserDetailsActivity.this.i.putExtra(ImagesContract.URL, ((HashMap) UserDetailsActivity.this.listmap.get(0)).get("img1").toString());
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.startActivity(userDetailsActivity.i);
			}
		});
		this.linear2.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass3 */

			public void onClick(View _view) {
				SketchwareUtil.showMessage(UserDetailsActivity.this.getApplicationContext(), "'".concat(UserDetailsActivity.this.textview_name.getText().toString().concat("' Copied To The Clipboard")));
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.getApplicationContext();
				((ClipboardManager) userDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", UserDetailsActivity.this.textview_name.getText().toString()));
			}
		});
		this.linear4.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass4 */

			public void onClick(View _view) {
				SketchwareUtil.showMessage(UserDetailsActivity.this.getApplicationContext(), "'".concat(UserDetailsActivity.this.textview_email.getText().toString().concat("' Copied To The Clipboard")));
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.getApplicationContext();
				((ClipboardManager) userDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", UserDetailsActivity.this.textview_email.getText().toString()));
			}
		});
		this.linear5.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass5 */

			public void onClick(View _view) {
				SketchwareUtil.showMessage(UserDetailsActivity.this.getApplicationContext(), "'".concat(UserDetailsActivity.this.textview_number.getText().toString().concat("' Copied To The Clipboard")));
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.getApplicationContext();
				((ClipboardManager) userDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", UserDetailsActivity.this.textview_number.getText().toString()));
			}
		});
		this.linear6.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass6 */

			public void onClick(View _view) {
				SketchwareUtil.showMessage(UserDetailsActivity.this.getApplicationContext(), "'".concat(UserDetailsActivity.this.textview_w_m.getText().toString().concat("' Copied To The Clipboard")));
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.getApplicationContext();
				((ClipboardManager) userDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", UserDetailsActivity.this.textview_w_m.getText().toString()));
			}
		});
		this.linear17.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass7 */

			public void onClick(View _view) {
				SketchwareUtil.showMessage(UserDetailsActivity.this.getApplicationContext(), "'".concat(UserDetailsActivity.this.textview_ifsc.getText().toString().concat("' Copied To The Clipboard")));
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.getApplicationContext();
				((ClipboardManager) userDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", UserDetailsActivity.this.textview_ifsc.getText().toString()));
			}
		});
		this.linear18.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass8 */

			public void onClick(View _view) {
				SketchwareUtil.showMessage(UserDetailsActivity.this.getApplicationContext(), "'".concat(UserDetailsActivity.this.textview_wallet.getText().toString().concat("' Copied To The Clipboard")));
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.getApplicationContext();
				((ClipboardManager) userDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", UserDetailsActivity.this.textview_wallet.getText().toString()));
			}
		});
		this.linear19.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass9 */

			public void onClick(View _view) {
				SketchwareUtil.showMessage(UserDetailsActivity.this.getApplicationContext(), "'".concat(UserDetailsActivity.this.textview_wnumber.getText().toString().concat("' Copied To The Clipboard")));
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.getApplicationContext();
				((ClipboardManager) userDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", UserDetailsActivity.this.textview_wnumber.getText().toString()));
			}
		});
		this.linear12.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass10 */

			public void onClick(View _view) {
				SketchwareUtil.showMessage(UserDetailsActivity.this.getApplicationContext(), "'".concat(UserDetailsActivity.this.textview_bal.getText().toString().concat("' Copied To The Clipboard")));
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.getApplicationContext();
				((ClipboardManager) userDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", UserDetailsActivity.this.textview_bal.getText().toString()));
			}
		});
		this.linear13.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass11 */

			public void onClick(View _view) {
				SketchwareUtil.showMessage(UserDetailsActivity.this.getApplicationContext(), "'".concat(UserDetailsActivity.this.textview_ammo.getText().toString().concat("' Copied To The Clipboard")));
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.getApplicationContext();
				((ClipboardManager) userDetailsActivity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", UserDetailsActivity.this.textview_ammo.getText().toString()));
			}
		});
		this.button1.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass12 */

			public void onClick(View _view) {
				UserDetailsActivity.this.c = Calendar.getInstance();
				UserDetailsActivity.this.map2 = new HashMap();
				UserDetailsActivity.this.map2.put("rs", ((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending_ammount").toString());
				UserDetailsActivity.this.map2.put("action", ((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString());
				UserDetailsActivity.this.map2.put("time", new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(UserDetailsActivity.this.c.getTime()));
				UserDetailsActivity.this.map2.put(NotificationCompat.CATEGORY_STATUS, "Cancelled");
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.path_noti = "userHistory/".concat(userDetailsActivity.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Transactions".concat("")));
				UserDetailsActivity.this.user_noti.removeEventListener(UserDetailsActivity.this._user_noti_child_listener);
				UserDetailsActivity userDetailsActivity2 = UserDetailsActivity.this;
				userDetailsActivity2.user_noti = userDetailsActivity2._firebase.getReference(UserDetailsActivity.this.path_noti);
				UserDetailsActivity.this.user_noti.addChildEventListener(UserDetailsActivity.this._user_noti_child_listener);
				UserDetailsActivity.this.user_noti.push().updateChildren(UserDetailsActivity.this.map2);
				if (((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString().equals("withdraw")) {
					UserDetailsActivity.this.map = new HashMap();
					UserDetailsActivity.this.map.put("pending", "clear");
					UserDetailsActivity.this.map.put("pending_ammount", "0");
					UserDetailsActivity.this.map.put("ref", "clear");
					UserDetailsActivity.this.map.put("img1", "null");
					UserDetailsActivity.this.user_h.child(UserDetailsActivity.this.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(UserDetailsActivity.this.map);
					UserDetailsActivity.this.users.child(UserDetailsActivity.this.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(UserDetailsActivity.this.map);
					UserDetailsActivity.this.map.clear();
					UserDetailsActivity.this.pd.show();
					UserDetailsActivity.this.t = new TimerTask() {
						/* class dream.fata.fat.UserDetailsActivity.AnonymousClass12.AnonymousClass1 */

						public void run() {
							UserDetailsActivity.this.runOnUiThread(new Runnable() {
								/* class dream.fata.fat.UserDetailsActivity.AnonymousClass12.AnonymousClass1.AnonymousClass1 */

								public void run() {
									UserDetailsActivity.this.pd.dismiss();
									UserDetailsActivity.this.finish();
								}
							});
						}
					};
					UserDetailsActivity.this._timer.schedule(UserDetailsActivity.this.t, 2000);
				} else if (((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString().equals("deposit")) {
					UserDetailsActivity.this.map = new HashMap();
					UserDetailsActivity.this.map.put("pending", "clear");
					UserDetailsActivity.this.map.put("pending_ammount", "0");
					UserDetailsActivity.this.map.put("ref", "clear");
					UserDetailsActivity.this.map.put("img1", "null");
					UserDetailsActivity.this.user_h.child(UserDetailsActivity.this.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(UserDetailsActivity.this.map);
					UserDetailsActivity.this.users.child(UserDetailsActivity.this.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(UserDetailsActivity.this.map);
					UserDetailsActivity.this.map.clear();
					UserDetailsActivity.this.pd.show();
					UserDetailsActivity.this.t = new TimerTask() {
						/* class dream.fata.fat.UserDetailsActivity.AnonymousClass12.AnonymousClass2 */

						public void run() {
							UserDetailsActivity.this.runOnUiThread(new Runnable() {
								/* class dream.fata.fat.UserDetailsActivity.AnonymousClass12.AnonymousClass2.AnonymousClass1 */

								public void run() {
									UserDetailsActivity.this.pd.dismiss();
									UserDetailsActivity.this.finish();
								}
							});
						}
					};
					UserDetailsActivity.this._timer.schedule(UserDetailsActivity.this.t, 2000);
				}
			}
		});
		this.button2.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass13 */

			public void onClick(View _view) {
				if (((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString().equals("withdraw")) {
					UserDetailsActivity.this.map = new HashMap();
					UserDetailsActivity.this.map.put("pending", "clear");
					UserDetailsActivity.this.map.put("pending_ammount", "0");
					UserDetailsActivity.this.map.put("ref", "clear");
					UserDetailsActivity.this.map.put("img1", "null");
					UserDetailsActivity.this.map.put("balance", String.valueOf((long) (UserDetailsActivity.this.balance - Double.parseDouble(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending_ammount").toString()))));
					UserDetailsActivity.this.user_h.child(UserDetailsActivity.this.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(UserDetailsActivity.this.map);
					UserDetailsActivity.this.users.child(UserDetailsActivity.this.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(UserDetailsActivity.this.map);
					UserDetailsActivity.this.map.clear();
					UserDetailsActivity.this.c = Calendar.getInstance();
					UserDetailsActivity.this.map2 = new HashMap();
					UserDetailsActivity.this.map2.put("rs", ((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending_ammount").toString());
					UserDetailsActivity.this.map2.put("action", ((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString());
					UserDetailsActivity.this.map2.put("time", new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(UserDetailsActivity.this.c.getTime()));
					UserDetailsActivity.this.map2.put(NotificationCompat.CATEGORY_STATUS, "Withdrawaled");
					UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
					userDetailsActivity.path_noti = "userHistory/".concat(userDetailsActivity.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Transactions".concat("")));
					UserDetailsActivity.this.user_noti.removeEventListener(UserDetailsActivity.this._user_noti_child_listener);
					UserDetailsActivity userDetailsActivity2 = UserDetailsActivity.this;
					userDetailsActivity2.user_noti = userDetailsActivity2._firebase.getReference(UserDetailsActivity.this.path_noti);
					UserDetailsActivity.this.user_noti.addChildEventListener(UserDetailsActivity.this._user_noti_child_listener);
					UserDetailsActivity.this.user_noti.push().updateChildren(UserDetailsActivity.this.map2);
					UserDetailsActivity.this.pd.show();
					UserDetailsActivity.this.t = new TimerTask() {
						/* class dream.fata.fat.UserDetailsActivity.AnonymousClass13.AnonymousClass1 */

						public void run() {
							UserDetailsActivity.this.runOnUiThread(new Runnable() {
								/* class dream.fata.fat.UserDetailsActivity.AnonymousClass13.AnonymousClass1.AnonymousClass1 */

								public void run() {
									UserDetailsActivity.this.pd.dismiss();
									UserDetailsActivity.this.finish();
								}
							});
						}
					};
					UserDetailsActivity.this._timer.schedule(UserDetailsActivity.this.t, 2000);
				} else if (((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString().equals("deposit")) {
					UserDetailsActivity.this.map = new HashMap();
					UserDetailsActivity.this.map.put("pending", "clear");
					UserDetailsActivity.this.map.put("pending_ammount", "0");
					UserDetailsActivity.this.map.put("ref", "clear");
					UserDetailsActivity.this.map.put("img1", "null");
					UserDetailsActivity.this.map.put("balance", String.valueOf((long) (UserDetailsActivity.this.balance + Double.parseDouble(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending_ammount").toString()))));
					UserDetailsActivity.this.user_h.child(UserDetailsActivity.this.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(UserDetailsActivity.this.map);
					UserDetailsActivity.this.users.child(UserDetailsActivity.this.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(UserDetailsActivity.this.map);
					UserDetailsActivity.this.map.clear();
					UserDetailsActivity.this.c = Calendar.getInstance();
					UserDetailsActivity.this.map2 = new HashMap();
					UserDetailsActivity.this.map2.put("rs", ((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending_ammount").toString());
					UserDetailsActivity.this.map2.put("action", ((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString());
					UserDetailsActivity.this.map2.put("time", new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(UserDetailsActivity.this.c.getTime()));
					UserDetailsActivity.this.map2.put(NotificationCompat.CATEGORY_STATUS, "Deposited");
					UserDetailsActivity userDetailsActivity3 = UserDetailsActivity.this;
					userDetailsActivity3.path_noti = "userHistory/".concat(userDetailsActivity3.getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", "").concat("/Transactions".concat("")));
					UserDetailsActivity.this.user_noti.removeEventListener(UserDetailsActivity.this._user_noti_child_listener);
					UserDetailsActivity userDetailsActivity4 = UserDetailsActivity.this;
					userDetailsActivity4.user_noti = userDetailsActivity4._firebase.getReference(UserDetailsActivity.this.path_noti);
					UserDetailsActivity.this.user_noti.addChildEventListener(UserDetailsActivity.this._user_noti_child_listener);
					UserDetailsActivity.this.user_noti.push().updateChildren(UserDetailsActivity.this.map2);
					UserDetailsActivity.this.pd.show();
					UserDetailsActivity.this.t = new TimerTask() {
						/* class dream.fata.fat.UserDetailsActivity.AnonymousClass13.AnonymousClass2 */

						public void run() {
							UserDetailsActivity.this.runOnUiThread(new Runnable() {
								/* class dream.fata.fat.UserDetailsActivity.AnonymousClass13.AnonymousClass2.AnonymousClass1 */

								public void run() {
									UserDetailsActivity.this.pd.dismiss();
									UserDetailsActivity.this.finish();
								}
							});
						}
					};
					UserDetailsActivity.this._timer.schedule(UserDetailsActivity.this.t, 2000);
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
		this._user_noti_child_listener = childEventListener;
		this.user_noti.addChildEventListener(childEventListener);
	}

	private void initializeLogic() {
		ProgressDialog progressDialog = new ProgressDialog(this);
		this.pd = progressDialog;
		progressDialog.setMessage("Loading");
		this.pd.setCancelable(false);
		this.linear2.setElevation(30.0f);
		this.linear2.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass17 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.linear4.setElevation(30.0f);
		this.linear4.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass18 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.linear5.setElevation(30.0f);
		this.linear5.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass19 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.linear6.setElevation(30.0f);
		this.linear6.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass20 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.linear7.setElevation(30.0f);
		this.linear7.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass21 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.linear12.setElevation(30.0f);
		this.linear12.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass22 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.linear14.setElevation(30.0f);
		this.linear14.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass23 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.linear17.setElevation(30.0f);
		this.linear17.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass24 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.linear18.setElevation(30.0f);
		this.linear18.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass25 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.linear19.setElevation(30.0f);
		this.linear19.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.UserDetailsActivity.AnonymousClass26 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(20, 1, -3155748, -1));
		this.path = "users/".concat(getIntent().getStringExtra("email").replace(" @", "").replace(".", "").replace(" ", ""));
		this.users.removeEventListener(this._users_child_listener);
		DatabaseReference reference = this._firebase.getReference(this.path);
		this.users = reference;
		reference.addChildEventListener(this._users_child_listener);
		this.users.addListenerForSingleValueEvent(new ValueEventListener() {
			public void onCancelled(DatabaseError param1DatabaseError) {}

			public void onDataChange(DataSnapshot param1DataSnapshot) {
				listmap = new ArrayList();
				try {
					GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

					};
					Iterator<DataSnapshot> iterator = param1DataSnapshot.getChildren().iterator();
					while (iterator.hasNext()) {
						HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
						UserDetailsActivity.this.listmap.add(hashMap);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				UserDetailsActivity.this.textview_ref.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("ref").toString());
				Glide.with(UserDetailsActivity.this.getApplicationContext()).load(Uri.parse(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("img1").toString())).into(UserDetailsActivity.this.imageview1);
				UserDetailsActivity.this.textview_name.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("name").toString());
				UserDetailsActivity.this.textview_email.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("email").toString());
				UserDetailsActivity.this.textview_number.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("number").toString());
				UserDetailsActivity.this.textview_bal.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("balance").toString());
				UserDetailsActivity userDetailsActivity = UserDetailsActivity.this;
				userDetailsActivity.balance = Double.parseDouble(((HashMap) userDetailsActivity.listmap.get(0)).get("balance").toString());
				UserDetailsActivity.this.textview_w_m.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("withdraw_bankName").toString());
				UserDetailsActivity.this.textview_w_d.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("withdraw_bankAcc").toString());
				UserDetailsActivity.this.textview_wnumber.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("withdraw_number").toString());
				UserDetailsActivity.this.textview_ifsc.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("withdraw_bankIFSC").toString());
				UserDetailsActivity.this.textview_wallet.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("withdraw_wallet").toString());
				UserDetailsActivity.this.textview_ammo.setText(((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending_ammount").toString());
				if (((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString().equals("clear")) {
					UserDetailsActivity.this.textview_pending_notice.setText("No Pending Request Available");
					UserDetailsActivity.this.linear14.setVisibility(8);
					UserDetailsActivity.this.linear11.setVisibility(8);
				} else if (((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString().equals("withdraw")) {
					UserDetailsActivity.this.textview_pending_notice.setText("You Have A Withdrawal Request From This User");
					UserDetailsActivity.this.textview13.setText("Withdrawal Amount :");
					UserDetailsActivity.this.imageview1.setVisibility(8);
				} else if (((HashMap) UserDetailsActivity.this.listmap.get(0)).get("pending").toString().equals("deposit")) {
					UserDetailsActivity.this.textview_pending_notice.setText("You Have A Deposit Request From This User");
					UserDetailsActivity.this.textview13.setText("Deposit Amount :");
				}
			}



		});
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
