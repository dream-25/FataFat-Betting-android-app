package dream.fata.fat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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

public class PlaySingleActivity extends AppCompatActivity {
	private ChildEventListener _add_bet_child_listener;
	private AppBarLayout _app_bar;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private CoordinatorLayout _coordinator;
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private ChildEventListener _play_now2_child_listener;
	private ChildEventListener _play_now_child_listener;
	private Timer _timer = new Timer();
	private Toolbar _toolbar;
	private ChildEventListener _user_h_child_listener;
	private ChildEventListener _user_logGames_child_listener;
	private ChildEventListener _user_logTransaction_child_listener;
	private ChildEventListener _user_log_child_listener;
	private ChildEventListener _users_child_listener;
	private DatabaseReference add_bet = this._firebase.getReference("add_bet");
	private FirebaseAuth auth;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private double balance = 0.0d;
	private String bazi_name = "";
	private Button button1;
	private Button button2;
	private Calendar c = Calendar.getInstance();
	private Calendar c1 = Calendar.getInstance();
	private Calendar c2 = Calendar.getInstance();
	private double current_time = 0.0d;
	private AlertDialog.Builder dialog;
	private EditText edittext1;
	private EditText edittext2;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap_add_bet = new ArrayList<>();
	private ArrayList<String> liststring_childkey_add_bet = new ArrayList<>();
	private ListView listview1;
	private double n_playNow = 0.0d;
	private String path_add_bet = "";
	private String path_playNow = "";
	private String path_playNow2 = "";
	private String path_user = "";
	private String path_userLog = "";
	private String path_user_logGames = "";
	private String path_user_logTransaction = "";
	private String path_userh = "";
	private ProgressDialog pd;
	private DatabaseReference play_now = this._firebase.getReference("play_now");
	private DatabaseReference play_now2 = this._firebase.getReference("play_now2");
	private HashMap<String, Object> play_user_his_games = new HashMap<>();
	private String playing = "";
	private double position_add_bet = 0.0d;
	private TimerTask t;
	private TimerTask t1;
	private TimerTask t2;
	private TextView textview1;
	private TextView textview10;
	private TextView textview2;
	private TextView textview3;
	private TextView textview4;
	private TextView textview7;
	private TextView textview8;
	private TextView textview9;
	private double total_playNow = 0.0d;
	private SharedPreferences user;
	private DatabaseReference user_h = this._firebase.getReference("user_h");
	private DatabaseReference user_log = this._firebase.getReference("user_log");
	private DatabaseReference user_logGames = this._firebase.getReference("user_logGames");
	private DatabaseReference user_logTransaction = this._firebase.getReference("user_logTransaction");
	private DatabaseReference users = this._firebase.getReference("users");
	private HashMap<String, Object> varMap_add_bet = new HashMap<>();
	private HashMap<String, Object> varMap_play_user_his = new HashMap<>();
	private HashMap<String, Object> varMap_playnow = new HashMap<>();
	private HashMap<String, Object> varMap_playnow2 = new HashMap<>();
	private HashMap<String, Object> varMap_user = new HashMap<>();

	static /* synthetic */ double access$708(PlaySingleActivity x0) {
		double d = x0.n_playNow;
		x0.n_playNow = 1.0d + d;
		return d;
	}

	/* access modifiers changed from: protected */
	@Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
	public void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.play_single);
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
			/* class dream.fata.fat.PlaySingleActivity.AnonymousClass1 */

			public void onClick(View _v) {
				PlaySingleActivity.this.onBackPressed();
			}
		});
		this.linear6 = (LinearLayout) findViewById(R.id.linear6);
		this.linear7 = (LinearLayout) findViewById(R.id.linear7);
		this.linear1 = (LinearLayout) findViewById(R.id.linear1);
		this.textview7 = (TextView) findViewById(R.id.textview7);
		this.linear2 = (LinearLayout) findViewById(R.id.linear2);
		this.button1 = (Button) findViewById(R.id.button1);
		this.linear5 = (LinearLayout) findViewById(R.id.linear5);
		this.listview1 = (ListView) findViewById(R.id.listview1);
		this.textview10 = (TextView) findViewById(R.id.textview10);
		this.button2 = (Button) findViewById(R.id.button2);
		this.textview8 = (TextView) findViewById(R.id.textview8);
		this.textview9 = (TextView) findViewById(R.id.textview9);
		this.textview1 = (TextView) findViewById(R.id.textview1);
		this.linear3 = (LinearLayout) findViewById(R.id.linear3);
		this.linear4 = (LinearLayout) findViewById(R.id.linear4);
		this.edittext1 = (EditText) findViewById(R.id.edittext1);
		this.edittext2 = (EditText) findViewById(R.id.edittext2);
		this.textview2 = (TextView) findViewById(R.id.textview2);
		this.textview4 = (TextView) findViewById(R.id.textview4);
		this.textview3 = (TextView) findViewById(R.id.textview3);
		this.auth = FirebaseAuth.getInstance();
		this.user = getSharedPreferences("user", 0);
		this.dialog = new AlertDialog.Builder(this);
		this.button1.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.PlaySingleActivity.AnonymousClass2 */

			public void onClick(View _view) {
				if (PlaySingleActivity.this.bazi_name.equals("GAME OVER")) {
					SketchwareUtil.showMessage(PlaySingleActivity.this.getApplicationContext(), "No Games Are Available Now");
				} else if (PlaySingleActivity.this.edittext1.getText().toString().equals("") || 9.0d >= Double.parseDouble(PlaySingleActivity.this.edittext2.getText().toString()) || Double.parseDouble(PlaySingleActivity.this.edittext2.getText().toString()) >= 50001.0d || PlaySingleActivity.this.edittext2.getText().toString().equals("")) {
					SketchwareUtil.showMessage(PlaySingleActivity.this.getApplicationContext(), "Select Digit Between 0-9 and Rs 10-50000");
				} else if (Double.parseDouble(PlaySingleActivity.this.edittext1.getText().toString()) >= 10.0d) {
					SketchwareUtil.showMessage(PlaySingleActivity.this.getApplicationContext(), "Select Digit Between 0-9");
				} else if (Double.parseDouble(PlaySingleActivity.this.edittext2.getText().toString()) >= PlaySingleActivity.this.balance) {
					SketchwareUtil.showMessage(PlaySingleActivity.this.getApplicationContext(), "Insufficient Balance");
				} else if (Double.parseDouble(PlaySingleActivity.this.edittext2.getText().toString()) + PlaySingleActivity.this.total_playNow < PlaySingleActivity.this.balance) {
					PlaySingleActivity.this.varMap_add_bet = new HashMap();
					PlaySingleActivity.this.varMap_add_bet.put("digit", PlaySingleActivity.this.edittext1.getText().toString());
					PlaySingleActivity.this.varMap_add_bet.put("rs", PlaySingleActivity.this.edittext2.getText().toString());
					PlaySingleActivity.this.add_bet.push().updateChildren(PlaySingleActivity.this.varMap_add_bet);
					PlaySingleActivity.this.edittext1.setText("");
					PlaySingleActivity.this.edittext2.setText("");
					SketchwareUtil.hideKeyboard(PlaySingleActivity.this.getApplicationContext());
				} else {
					SketchwareUtil.showMessage(PlaySingleActivity.this.getApplicationContext(), "Insufficient Balance");
				}
			}
		});
		this.button2.setOnClickListener(new View.OnClickListener() {
			/* class dream.fata.fat.PlaySingleActivity.AnonymousClass3 */

			public void onClick(View _view) {
				if (PlaySingleActivity.this.bazi_name.equals("GAME OVER")) {
					SketchwareUtil.showMessage(PlaySingleActivity.this.getApplicationContext(), "No Games Are Available Now");
					return;
				}
				PlaySingleActivity.this.total_playNow = 0.0d;
				PlaySingleActivity.this.n_playNow = 0.0d;
				if (PlaySingleActivity.this.listmap_add_bet.size() > 0) {
					for (int _repeat10 = 0; _repeat10 < PlaySingleActivity.this.listmap_add_bet.size(); _repeat10++) {
						PlaySingleActivity.this.playing = "true";
						PlaySingleActivity.this.pd.show();
						PlaySingleActivity.this.c1 = Calendar.getInstance();
						PlaySingleActivity.this.varMap_user = new HashMap();
						PlaySingleActivity.this.balance -= Double.parseDouble(((HashMap) PlaySingleActivity.this.listmap_add_bet.get((int) PlaySingleActivity.this.n_playNow)).get("rs").toString());
						PlaySingleActivity.this.varMap_user.put("balance", String.valueOf((long) PlaySingleActivity.this.balance));
						PlaySingleActivity.this.users.child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(PlaySingleActivity.this.varMap_user);
						PlaySingleActivity.this.user_h.child(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "")).updateChildren(PlaySingleActivity.this.varMap_user);
						PlaySingleActivity.this.users.addChildEventListener(PlaySingleActivity.this._users_child_listener);
						PlaySingleActivity.this.varMap_playnow = new HashMap();
						PlaySingleActivity.this.varMap_playnow.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
						PlaySingleActivity.this.varMap_playnow.put("digit", ((HashMap) PlaySingleActivity.this.listmap_add_bet.get((int) PlaySingleActivity.this.n_playNow)).get("digit").toString());
						PlaySingleActivity.this.varMap_playnow.put("rs", ((HashMap) PlaySingleActivity.this.listmap_add_bet.get((int) PlaySingleActivity.this.n_playNow)).get("rs").toString());
						PlaySingleActivity.this.c2 = Calendar.getInstance();
						PlaySingleActivity playSingleActivity = PlaySingleActivity.this;
						playSingleActivity.path_playNow = "playNow_single/".concat(playSingleActivity.getIntent().getStringExtra("Game").concat("/")).concat(new SimpleDateFormat("dd-MM-yyyy").format(PlaySingleActivity.this.c2.getTime())).concat("/").concat(PlaySingleActivity.this.bazi_name);
						PlaySingleActivity.this.play_now.removeEventListener(PlaySingleActivity.this._play_now_child_listener);
						PlaySingleActivity playSingleActivity2 = PlaySingleActivity.this;
						playSingleActivity2.play_now = playSingleActivity2._firebase.getReference(PlaySingleActivity.this.path_playNow);
						PlaySingleActivity.this.play_now.addChildEventListener(PlaySingleActivity.this._play_now_child_listener);
						PlaySingleActivity.this.play_now.push().updateChildren(PlaySingleActivity.this.varMap_playnow);
						PlaySingleActivity.this.varMap_playnow2 = new HashMap();
						PlaySingleActivity.this.varMap_playnow2.put("digit", ((HashMap) PlaySingleActivity.this.listmap_add_bet.get((int) PlaySingleActivity.this.n_playNow)).get("digit").toString());
						PlaySingleActivity playSingleActivity3 = PlaySingleActivity.this;
						playSingleActivity3.path_playNow2 = "playNow_single_Digits/".concat(playSingleActivity3.getIntent().getStringExtra("Game").concat("/")).concat(new SimpleDateFormat("dd-MM-yyyy").format(PlaySingleActivity.this.c2.getTime())).concat("/").concat(PlaySingleActivity.this.bazi_name);
						PlaySingleActivity.this.play_now2.removeEventListener(PlaySingleActivity.this._play_now2_child_listener);
						PlaySingleActivity playSingleActivity4 = PlaySingleActivity.this;
						playSingleActivity4.play_now2 = playSingleActivity4._firebase.getReference(PlaySingleActivity.this.path_playNow2);
						PlaySingleActivity.this.play_now2.addChildEventListener(PlaySingleActivity.this._play_now2_child_listener);
						PlaySingleActivity.this.play_now2.child(((HashMap) PlaySingleActivity.this.listmap_add_bet.get((int) PlaySingleActivity.this.n_playNow)).get("digit").toString()).updateChildren(PlaySingleActivity.this.varMap_playnow2);
						PlaySingleActivity.this.path_user_logGames = "userHistory/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "").concat("/Games/".concat(PlaySingleActivity.this.getIntent().getStringExtra("Game").concat("_Single"))));
						PlaySingleActivity.this.user_logGames.removeEventListener(PlaySingleActivity.this._user_logGames_child_listener);
						PlaySingleActivity playSingleActivity5 = PlaySingleActivity.this;
						playSingleActivity5.user_logGames = playSingleActivity5._firebase.getReference(PlaySingleActivity.this.path_user_logGames);
						PlaySingleActivity.this.user_logGames.addChildEventListener(PlaySingleActivity.this._user_logGames_child_listener);
						PlaySingleActivity.this.varMap_play_user_his = new HashMap();
						PlaySingleActivity.this.varMap_play_user_his.put("bazi", PlaySingleActivity.this.bazi_name);
						PlaySingleActivity.this.varMap_play_user_his.put("digit", ((HashMap) PlaySingleActivity.this.listmap_add_bet.get((int) PlaySingleActivity.this.n_playNow)).get("digit").toString());
						PlaySingleActivity.this.varMap_play_user_his.put("rs", ((HashMap) PlaySingleActivity.this.listmap_add_bet.get((int) PlaySingleActivity.this.n_playNow)).get("rs").toString());
						PlaySingleActivity.this.varMap_play_user_his.put(NotificationCompat.CATEGORY_STATUS, "Bet Added");
						PlaySingleActivity.this.varMap_play_user_his.put("time", new SimpleDateFormat("dd-MM-yyyy hh:mm aa").format(PlaySingleActivity.this.c.getTime()));
						PlaySingleActivity.this.user_logGames.push().updateChildren(PlaySingleActivity.this.varMap_play_user_his);
						PlaySingleActivity.this.varMap_play_user_his.clear();
						PlaySingleActivity.this.varMap_playnow.clear();
						PlaySingleActivity.this.varMap_playnow2.clear();
						PlaySingleActivity.this.varMap_play_user_his.clear();
						PlaySingleActivity.this.play_user_his_games.clear();
						PlaySingleActivity.this.add_bet.child((String) PlaySingleActivity.this.liststring_childkey_add_bet.get((int) PlaySingleActivity.this.n_playNow)).removeValue();
						PlaySingleActivity.access$708(PlaySingleActivity.this);
					}
					if (((double) PlaySingleActivity.this.listmap_add_bet.size()) == PlaySingleActivity.this.n_playNow) {
						PlaySingleActivity.this.listmap_add_bet.clear();
						PlaySingleActivity.this.liststring_childkey_add_bet.clear();
						ListView listView = PlaySingleActivity.this.listview1;
						PlaySingleActivity playSingleActivity6 = PlaySingleActivity.this;
						listView.setAdapter((ListAdapter) new Listview1Adapter(playSingleActivity6.listmap_add_bet));
						((BaseAdapter) PlaySingleActivity.this.listview1.getAdapter()).notifyDataSetChanged();
						PlaySingleActivity.this.users.addListenerForSingleValueEvent(new ValueEventListener() {

							public void onDataChange(DataSnapshot param2DataSnapshot) {
								listmap = new ArrayList();
								try {
									GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

									};
									Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
									while (iterator.hasNext()) {
										HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
										PlaySingleActivity.this.listmap.add(hashMap);
									}
								} catch (Exception exception) {
									exception.printStackTrace();
								}
								PlaySingleActivity.this.user.edit().putString("name", ((HashMap) PlaySingleActivity.this.listmap.get(0)).get("name").toString()).commit();
								PlaySingleActivity.this.user.edit().putString("email", ((HashMap) PlaySingleActivity.this.listmap.get(0)).get("email").toString()).commit();
								PlaySingleActivity.this.user.edit().putString("number", ((HashMap) PlaySingleActivity.this.listmap.get(0)).get("number").toString()).commit();
								PlaySingleActivity.this.user.edit().putString("balance", ((HashMap) PlaySingleActivity.this.listmap.get(0)).get("balance").toString()).commit();
								PlaySingleActivity.this.balance = Double.parseDouble(((HashMap) PlaySingleActivity.this.listmap.get(0)).get("balance").toString());
								PlaySingleActivity.this.textview7.setText("Balance : ".concat("₹ ").concat(((HashMap) PlaySingleActivity.this.listmap.get(0)).get("balance").toString()));
								PlaySingleActivity.this.t2 = new TimerTask() {
									/* class dream.fata.fat.PlaySingleActivity.AnonymousClass3.AnonymousClass1.AnonymousClass2 */

									public void run() {
										PlaySingleActivity.this.runOnUiThread(new Runnable() {
											/* class dream.fata.fat.PlaySingleActivity.AnonymousClass3.AnonymousClass1.AnonymousClass2.AnonymousClass1 */

											public void run() {
												PlaySingleActivity.this.playing = "false";
												PlaySingleActivity.this.textview10.setText("Total Betting Amount : 0");
												PlaySingleActivity.this.pd.dismiss();
											}
										});
									}
								};
								PlaySingleActivity.this._timer.schedule(PlaySingleActivity.this.t2, 1500);
							}

							@Override // com.google.firebase.database.ValueEventListener
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						return;
					}
					return;
				}
				SketchwareUtil.showMessage(PlaySingleActivity.this.getApplicationContext(), "Add Bettings Before Playing");
			}
		});
		this.edittext1.addTextChangedListener(new TextWatcher() {
			/* class dream.fata.fat.PlaySingleActivity.AnonymousClass4 */

			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				String _charSeq = _param1.toString();
				if (_charSeq.length() > 1) {
					PlaySingleActivity.this.edittext1.setText(_charSeq.substring(0, 1));
				}
			}

			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
			}

			public void afterTextChanged(Editable _param1) {
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
				param1String = param1DataSnapshot.getKey();
				HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
				PlaySingleActivity.this.liststring_childkey_add_bet.add(param1String);
				PlaySingleActivity.this.add_bet.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						listmap_add_bet = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								PlaySingleActivity.this.listmap_add_bet.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						PlaySingleActivity.this.listview1.setAdapter((ListAdapter)new PlaySingleActivity.Listview1Adapter(PlaySingleActivity.this.listmap_add_bet));
						((BaseAdapter)PlaySingleActivity.this.listview1.getAdapter()).notifyDataSetChanged();
					}
				});
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
				if (PlaySingleActivity.this.playing.equals("true"))
					return;
				ListView listView = PlaySingleActivity.this.listview1;
				PlaySingleActivity playSingleActivity = PlaySingleActivity.this;
				listView.setAdapter((ListAdapter)new PlaySingleActivity.Listview1Adapter(playSingleActivity.listmap_add_bet));
				((BaseAdapter)PlaySingleActivity.this.listview1.getAdapter()).notifyDataSetChanged();
				PlaySingleActivity.this.pd.dismiss();
				PlaySingleActivity.this.liststring_childkey_add_bet.remove((int)PlaySingleActivity.this.position_add_bet);
				PlaySingleActivity.this.listmap_add_bet.remove((int)PlaySingleActivity.this.position_add_bet);
			}
		};
		this._add_bet_child_listener = childEventListener;
		this.add_bet.addChildEventListener(childEventListener);
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
		this._user_log_child_listener = childEventListener;
		this.user_log.addChildEventListener(childEventListener);
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
		this._user_logTransaction_child_listener = childEventListener;
		this.user_logTransaction.addChildEventListener(childEventListener);
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
		this._play_now2_child_listener = childEventListener;
		this.play_now2.addChildEventListener(childEventListener);
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
		this.auth_updateEmailListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_updatePasswordListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_deleteUserListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			public void onComplete(Task<AuthResult> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_updateProfileListener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this.auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			public void onComplete(Task<AuthResult> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this._auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			public void onComplete(Task<AuthResult> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this._auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			public void onComplete(Task<AuthResult> param1Task) {
				param1Task.isSuccessful();
				if (param1Task.getException() != null) {
					param1Task.getException().getMessage();
					return;
				}
			}
		};
		this._auth_reset_password_listener = new OnCompleteListener<Void>() {
			public void onComplete(Task<Void> param1Task) {
				param1Task.isSuccessful();
			}
		};
	}

	private void initializeLogic() {
		this.c = Calendar.getInstance();
		ProgressDialog progressDialog = new ProgressDialog(this);
		this.pd = progressDialog;
		progressDialog.setMessage("Loading");
		this.pd.setCancelable(false);
		this.linear2.setElevation(30.0f);
		this.linear2.setBackground(new GradientDrawable() {
			/* class dream.fata.fat.PlaySingleActivity.AnonymousClass23 */

			public GradientDrawable getIns(int a, int b, int c, int d) {
				setCornerRadius((float) a);
				setStroke(b, c);
				setColor(d);
				return this;
			}
		}.getIns(25, 1, -10453621, -1));
		this.textview1.setText("Place Your Bet ".concat("[".concat(getIntent().getStringExtra("Game").concat("]"))));
		if (getIntent().getStringExtra("Game").equals("Fatafat")) {
			TimerTask r4 = new TimerTask() {
				/* class dream.fata.fat.PlaySingleActivity.AnonymousClass24 */

				public void run() {
					PlaySingleActivity.this.runOnUiThread(new Runnable() {
						/* class dream.fata.fat.PlaySingleActivity.AnonymousClass24.AnonymousClass1 */

						public void run() {
							PlaySingleActivity.this.c = Calendar.getInstance();
							PlaySingleActivity.this.current_time = (Double.parseDouble(new SimpleDateFormat("HH").format(PlaySingleActivity.this.c.getTime())) * 60.0d) + Double.parseDouble(new SimpleDateFormat("mm").format(PlaySingleActivity.this.c.getTime()));
							if (590.0d == PlaySingleActivity.this.current_time || 680.0d == PlaySingleActivity.this.current_time || 770.0d == PlaySingleActivity.this.current_time || 860.0d == PlaySingleActivity.this.current_time || 950.0d == PlaySingleActivity.this.current_time || 1040.0d == PlaySingleActivity.this.current_time || 1130.0d == PlaySingleActivity.this.current_time || 1220.0d == PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "GAME OVER";
								PlaySingleActivity.this.textview1.setText(PlaySingleActivity.this.bazi_name);
							} else if (590.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "1BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 9:50 AM]"));
							} else if (680.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "2BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 11:20 AM]"));
							} else if (770.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "3BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 12:50 PM]"));
							} else if (860.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "4BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 02:20 PM]"));
							} else if (950.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "5BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 03:50 PM]"));
							} else if (1040.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "6BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 05:20 PM]"));
							} else if (1130.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "7BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 06:50 PM]"));
							} else if (1220.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "8BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 08:20 PM]"));
							} else {
								PlaySingleActivity.this.bazi_name = "GAME OVER";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Wait Until Tomorrow]"));
							}
						}
					});
				}
			};
			this.t = r4;
			this._timer.scheduleAtFixedRate(r4, 1000, 1000);
		} else if (getIntent().getStringExtra("Game").equals("SILIGURI")) {
			TimerTask r42 = new TimerTask() {
				/* class dream.fata.fat.PlaySingleActivity.AnonymousClass25 */

				public void run() {
					PlaySingleActivity.this.runOnUiThread(new Runnable() {
						/* class dream.fata.fat.PlaySingleActivity.AnonymousClass25.AnonymousClass1 */

						public void run() {
							PlaySingleActivity.this.c = Calendar.getInstance();
							PlaySingleActivity.this.current_time = (Double.parseDouble(new SimpleDateFormat("HH").format(PlaySingleActivity.this.c.getTime())) * 60.0d) + Double.parseDouble(new SimpleDateFormat("mm").format(PlaySingleActivity.this.c.getTime()));
							if (590.0d == PlaySingleActivity.this.current_time || 680.0d == PlaySingleActivity.this.current_time || 770.0d == PlaySingleActivity.this.current_time || 860.0d == PlaySingleActivity.this.current_time || 950.0d == PlaySingleActivity.this.current_time || 1040.0d == PlaySingleActivity.this.current_time || 1130.0d == PlaySingleActivity.this.current_time || 1220.0d == PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "GAME OVER";
								PlaySingleActivity.this.textview1.setText(PlaySingleActivity.this.bazi_name);
							} else if (590.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "1BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 9:50 AM]"));
							} else if (680.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "2BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 11:20 AM]"));
							} else if (770.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "3BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 12:50 PM]"));
							} else if (860.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "4BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 02:20 PM]"));
							} else if (950.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "5BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 03:50 PM]"));
							} else if (1040.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "6BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 05:20 PM]"));
							} else if (1130.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "7BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 06:50 PM]"));
							} else if (1220.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "8BAZI";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 08:20 PM]"));
							} else {
								PlaySingleActivity.this.bazi_name = "GAME OVER";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Wait Until Tomorrow]"));
							}
						}
					});
				}
			};
			this.t = r42;
			this._timer.scheduleAtFixedRate(r42, 1000, 1000);
		} else if (getIntent().getStringExtra("Game").equals("Main Bazar")) {
			TimerTask r2 = new TimerTask() {
				/* class dream.fata.fat.PlaySingleActivity.AnonymousClass26 */

				public void run() {
					PlaySingleActivity.this.runOnUiThread(new Runnable() {

						public void run() {
							PlaySingleActivity.this.c = Calendar.getInstance();
							PlaySingleActivity.this.current_time = (Double.parseDouble(new SimpleDateFormat("HH").format(PlaySingleActivity.this.c.getTime())) * 60.0d) + Double.parseDouble(new SimpleDateFormat("mm").format(PlaySingleActivity.this.c.getTime()));
							if (PlaySingleActivity.this.current_time == 1285.0d || PlaySingleActivity.this.current_time == 1420.0d) {
								PlaySingleActivity.this.bazi_name = "GAME OVER";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name);
							} else if (1285.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "OPEN";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 09:25 PM]"));
							} else if (1420.0d > PlaySingleActivity.this.current_time) {
								PlaySingleActivity.this.bazi_name = "CLOSE";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Last Play Time 11:40 PM]"));
							} else {
								PlaySingleActivity.this.bazi_name = "GAME OVER";
								PlaySingleActivity.this.textview8.setText(PlaySingleActivity.this.bazi_name.concat(" [Wait For Tomorrow]"));
							}
						}
					});
				}
			};
			this.t = r2;
			this._timer.scheduleAtFixedRate(r2, 1000, 1000);
		}
	}

	@Override // androidx.fragment.app.FragmentActivity
	public void onResume() {
		super.onResume();
		this.path_user = "users/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", ""));
		this.users.removeEventListener(this._users_child_listener);
		DatabaseReference reference = this._firebase.getReference(this.path_user);
		this.users = reference;
		reference.addChildEventListener(this._users_child_listener);
		this.users.addListenerForSingleValueEvent(new ValueEventListener() {

			public void onDataChange(DataSnapshot param1DataSnapshot) {
				listmap = new ArrayList();
				try {
					GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

					};
					Iterator<DataSnapshot> iterator = param1DataSnapshot.getChildren().iterator();
					while (iterator.hasNext()) {
						HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
						PlaySingleActivity.this.listmap.add(hashMap);
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				PlaySingleActivity.this.user.edit().putString("name", ((HashMap) PlaySingleActivity.this.listmap.get(0)).get("name").toString()).commit();
				PlaySingleActivity.this.user.edit().putString("email", ((HashMap) PlaySingleActivity.this.listmap.get(0)).get("email").toString()).commit();
				PlaySingleActivity.this.user.edit().putString("number", ((HashMap) PlaySingleActivity.this.listmap.get(0)).get("number").toString()).commit();
				PlaySingleActivity.this.user.edit().putString("balance", ((HashMap) PlaySingleActivity.this.listmap.get(0)).get("balance").toString()).commit();
				PlaySingleActivity playSingleActivity = PlaySingleActivity.this;
				playSingleActivity.balance = Double.parseDouble(((HashMap) playSingleActivity.listmap.get(0)).get("balance").toString());
				PlaySingleActivity.this.textview7.setText("Balance : ".concat("₹").concat(((HashMap) PlaySingleActivity.this.listmap.get(0)).get("balance").toString()));
			}

			@Override // com.google.firebase.database.ValueEventListener
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		this.path_add_bet = "add_bet_single/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", "").concat("/")).concat(getIntent().getStringExtra("Game")).concat("").concat("");
		this.add_bet.removeEventListener(this._add_bet_child_listener);
		DatabaseReference reference2 = this._firebase.getReference(this.path_add_bet);
		this.add_bet = reference2;
		reference2.addChildEventListener(this._add_bet_child_listener);
		TimerTask r3 = new TimerTask() {
			/* class dream.fata.fat.PlaySingleActivity.AnonymousClass28 */

			public void run() {
				PlaySingleActivity.this.runOnUiThread(new Runnable() {
					/* class dream.fata.fat.PlaySingleActivity.AnonymousClass28.AnonymousClass1 */

					public void run() {
						PlaySingleActivity.this.n_playNow = 0.0d;
						PlaySingleActivity.this.total_playNow = 0.0d;
						for (int _repeat53 = 0; _repeat53 < PlaySingleActivity.this.listmap_add_bet.size(); _repeat53++) {
							PlaySingleActivity.this.total_playNow += Double.parseDouble(((HashMap) PlaySingleActivity.this.listmap_add_bet.get((int) PlaySingleActivity.this.n_playNow)).get("rs").toString());
							PlaySingleActivity.access$708(PlaySingleActivity.this);
							if (PlaySingleActivity.this.n_playNow == ((double) PlaySingleActivity.this.listmap_add_bet.size())) {
								PlaySingleActivity.this.textview10.setText("Total Betting Amount : ".concat(String.valueOf((long) PlaySingleActivity.this.total_playNow)));
							}
						}
					}
				});
			}
		};
		this.t1 = r3;
		this._timer.scheduleAtFixedRate(r3, 300, 300);
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

		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) PlaySingleActivity.this.getBaseContext().getSystemService("layout_inflater");
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.play_game, (ViewGroup) null);
			}
			LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			LinearLayout linearLayout = (LinearLayout) _view.findViewById(R.id.linear1);
			TextView textView = (TextView) _view.findViewById(R.id.textview4);
			linear2.setElevation(30.0f);
			linear2.setBackground(new GradientDrawable() {
				/* class dream.fata.fat.PlaySingleActivity.Listview1Adapter.AnonymousClass1 */

				public GradientDrawable getIns(int a, int b, int c, int d) {
					setCornerRadius((float) a);
					setStroke(b, c);
					setColor(d);
					return this;
				}
			}.getIns(20, 1, -14273992, -1));
			((TextView) _view.findViewById(R.id.textview1)).setText(this._data.get(_position).get("digit").toString());
			((TextView) _view.findViewById(R.id.textview2)).setText(this._data.get(_position).get("rs").toString());
			((Button) _view.findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
				/* class dream.fata.fat.PlaySingleActivity.Listview1Adapter.AnonymousClass2 */

				public void onClick(View _view) {
					PlaySingleActivity.this.dialog.setTitle("Delete");
					PlaySingleActivity.this.dialog.setMessage("Are You Sure To Delete This Bet?");
					PlaySingleActivity.this.dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
						/* class dream.fata.fat.PlaySingleActivity.Listview1Adapter.AnonymousClass2.AnonymousClass1 */

						public void onClick(DialogInterface _dialog, int _which) {
							PlaySingleActivity.this.pd.show();
							PlaySingleActivity.this.add_bet.child((String) PlaySingleActivity.this.liststring_childkey_add_bet.get(_position)).removeValue();
							PlaySingleActivity.this.position_add_bet = (double) _position;
						}
					});
					PlaySingleActivity.this.dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						/* class dream.fata.fat.PlaySingleActivity.Listview1Adapter.AnonymousClass2.AnonymousClass2 */

						public void onClick(DialogInterface _dialog, int _which) {
						}
					});
					PlaySingleActivity.this.dialog.create().show();
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
