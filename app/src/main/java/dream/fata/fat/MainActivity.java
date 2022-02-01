package dream.fata.fat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private AppBarLayout _app_bar;
    private OnCompleteListener<AuthResult> _auth_create_user_listener;
    private OnCompleteListener<Void> _auth_reset_password_listener;
    private OnCompleteListener<AuthResult> _auth_sign_in_listener;
    private CoordinatorLayout _coordinator;
    private DrawerLayout _drawer;
    private ImageView _drawer_imageview10;
    private ImageView _drawer_imageview11;
    private ImageView _drawer_imageview12;
    private ImageView _drawer_imageview13;
    private ImageView _drawer_imageview14;
    private ImageView _drawer_imageview15;
    private ImageView _drawer_imageview16;
    private ImageView _drawer_imageview9;
    private LinearLayout _drawer_linear1;
    private LinearLayout _drawer_linear2;
    private LinearLayout _drawer_linear3;
    private LinearLayout _drawer_linear4;
    private LinearLayout _drawer_linear5;
    private LinearLayout _drawer_linear_g_time;
    private LinearLayout _drawer_linear_home;
    private LinearLayout _drawer_linear_logout;
    private LinearLayout _drawer_linear_my_acc;
    private LinearLayout _drawer_linear_request_money;
    private LinearLayout _drawer_linear_rules;
    private LinearLayout _drawer_linear_share;
    private LinearLayout _drawer_linear_withdraw;
    private TextView _drawer_textview1;
    private TextView _drawer_textview10;
    private TextView _drawer_textview11;
    private TextView _drawer_textview12;
    private TextView _drawer_textview2;
    private TextView _drawer_textview3;
    private TextView _drawer_textview4;
    private TextView _drawer_textview5;
    private TextView _drawer_textview6;
    private TextView _drawer_textview7;
    private TextView _drawer_textview8;
    private TextView _drawer_textview9;
    private ScrollView _drawer_vscroll1;
    private FloatingActionButton _fab;
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private ChildEventListener _game_avail_child_listener;
    private Toolbar _toolbar;
    private ChildEventListener _users_child_listener;
    private String a = "";
    private SharedPreferences admin;
    private FirebaseAuth auth;
    private OnCompleteListener<Void> auth_deleteUserListener;
    private OnCompleteListener<Void> auth_emailVerificationSentListener;
    private OnCompleteListener<AuthResult> auth_googleSignInListener;
    private OnCompleteListener<AuthResult> auth_phoneAuthListener;
    private OnCompleteListener<Void> auth_updateEmailListener;
    private OnCompleteListener<Void> auth_updatePasswordListener;
    private OnCompleteListener<Void> auth_updateProfileListener;
    private String b = "";
    private Button button1;
    private Button button2;
    private Button button_add_money;
    private Button button_fatafat;
    private Button button_goa_kalyan;
    private Button button_main_bazar;
    private Button button_result_fatafat;
    private Button button_result_goa_kalyan;
    private Button button_result_main_bazar;
    private Button button_withdraw;
    private Intent call = new Intent();
    private Intent drawer_navigators = new Intent();
    private DatabaseReference game_avail = this._firebase.getReference("game_avail");
    private Intent home_navigators = new Intent();
    private Intent i = new Intent();
    private ImageView imageview1;
    private ImageView imageview2;
    private LinearLayout linear1;
    private LinearLayout linear11;
    private LinearLayout linear12;
    private LinearLayout linear13;
    private LinearLayout linear14;
    private LinearLayout linear15;
    private LinearLayout linear17;
    private LinearLayout linear19;
    private LinearLayout linear2;
    private LinearLayout linear20;
    private LinearLayout linear21;
    private LinearLayout linear22;
    private LinearLayout linear23;
    private LinearLayout linear24;
    private LinearLayout linear25;
    private LinearLayout linear3;
    private LinearLayout linear35;
    private LinearLayout linear36;
    private LinearLayout linear4;
    private LinearLayout linear7;
    private LinearLayout linear8;
    private LinearLayout linear_act;
    private LinearLayout linear_call_us;
    private LinearLayout linear_play_games;
    private LinearLayout linear_results;
    private LinearLayout linear_whatsapp;
    private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> listmap_game_avail = new ArrayList<>();
    private String path = "";
    private ProgressDialog pd;
    private TextView textview1;
    private TextView textview2;
    private TextView textview3;
    private TextView textview4;
    private TextView textview5;
    private SharedPreferences user;
    private DatabaseReference users = this._firebase.getReference("users");
    private ScrollView vscroll1;
    private Intent wp = new Intent();

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.main);
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
            /* class dream.fata.fat.MainActivity.AnonymousClass1 */

            public void onClick(View _v) {
                MainActivity.this.onBackPressed();
            }
        });
        this._fab = (FloatingActionButton) findViewById(R.id._fab);
        this._drawer = (DrawerLayout) findViewById(R.id._drawer);
        ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(this, this._drawer, this._toolbar, R.string.app_name, R.string.app_name);
        this._drawer.addDrawerListener(_toggle);
        _toggle.syncState();
        LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
        this.linear1 = (LinearLayout) findViewById(R.id.linear1);
        this.linear3 = (LinearLayout) findViewById(R.id.linear3);
        this.linear2 = (LinearLayout) findViewById(R.id.linear2);
        this.vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
        this.linear7 = (LinearLayout) findViewById(R.id.linear7);
        this.linear_play_games = (LinearLayout) findViewById(R.id.linear_play_games);
        this.linear_results = (LinearLayout) findViewById(R.id.linear_results);
        this.linear_act = (LinearLayout) findViewById(R.id.linear_act);
        this.linear35 = (LinearLayout) findViewById(R.id.linear35);
        this.linear36 = (LinearLayout) findViewById(R.id.linear36);
        this.linear8 = (LinearLayout) findViewById(R.id.linear8);
        this.linear11 = (LinearLayout) findViewById(R.id.linear11);
        this.linear12 = (LinearLayout) findViewById(R.id.linear12);
        this.textview3 = (TextView) findViewById(R.id.textview3);
        this.button_fatafat = (Button) findViewById(R.id.button_fatafat);
        this.linear13 = (LinearLayout) findViewById(R.id.linear13);
        this.linear14 = (LinearLayout) findViewById(R.id.linear14);
        this.button_goa_kalyan = (Button) findViewById(R.id.button_goa_kalyan);
        this.button_main_bazar = (Button) findViewById(R.id.button_main_bazar);
        this.linear15 = (LinearLayout) findViewById(R.id.linear15);
        this.linear17 = (LinearLayout) findViewById(R.id.linear17);
        this.linear19 = (LinearLayout) findViewById(R.id.linear19);
        this.textview4 = (TextView) findViewById(R.id.textview4);
        this.button_result_fatafat = (Button) findViewById(R.id.button_result_fatafat);
        this.linear20 = (LinearLayout) findViewById(R.id.linear20);
        this.linear21 = (LinearLayout) findViewById(R.id.linear21);
        this.button_result_goa_kalyan = (Button) findViewById(R.id.button_result_goa_kalyan);
        this.button_result_main_bazar = (Button) findViewById(R.id.button_result_main_bazar);
        this.linear22 = (LinearLayout) findViewById(R.id.linear22);
        this.linear23 = (LinearLayout) findViewById(R.id.linear23);
        this.textview5 = (TextView) findViewById(R.id.textview5);
        this.linear24 = (LinearLayout) findViewById(R.id.linear24);
        this.linear25 = (LinearLayout) findViewById(R.id.linear25);
        this.button_add_money = (Button) findViewById(R.id.button_add_money);
        this.button_withdraw = (Button) findViewById(R.id.button_withdraw);
        this.button1 = (Button) findViewById(R.id.button1);
        this.button2 = (Button) findViewById(R.id.button2);
        this.linear_call_us = (LinearLayout) findViewById(R.id.linear_call_us);
        this.linear4 = (LinearLayout) findViewById(R.id.linear4);
        this.linear_whatsapp = (LinearLayout) findViewById(R.id.linear_whatsapp);
        this.imageview1 = (ImageView) findViewById(R.id.imageview1);
        this.textview1 = (TextView) findViewById(R.id.textview1);
        this.imageview2 = (ImageView) findViewById(R.id.imageview2);
        this.textview2 = (TextView) findViewById(R.id.textview2);
        this._drawer_vscroll1 = (ScrollView) _nav_view.findViewById(R.id.vscroll1);
        this._drawer_linear1 = (LinearLayout) _nav_view.findViewById(R.id.linear1);
        this._drawer_linear2 = (LinearLayout) _nav_view.findViewById(R.id.linear2);
        this._drawer_linear_home = (LinearLayout) _nav_view.findViewById(R.id.linear_home);
        this._drawer_linear_request_money = (LinearLayout) _nav_view.findViewById(R.id.linear_request_money);
        this._drawer_linear_withdraw = (LinearLayout) _nav_view.findViewById(R.id.linear_withdraw);
        this._drawer_linear_my_acc = (LinearLayout) _nav_view.findViewById(R.id.linear_my_acc);
        this._drawer_linear_g_time = (LinearLayout) _nav_view.findViewById(R.id.linear_g_time);
        this._drawer_linear_rules = (LinearLayout) _nav_view.findViewById(R.id.linear_rules);
        this._drawer_linear_share = (LinearLayout) _nav_view.findViewById(R.id.linear_share);
        this._drawer_linear_logout = (LinearLayout) _nav_view.findViewById(R.id.linear_logout);
        this._drawer_linear3 = (LinearLayout) _nav_view.findViewById(R.id.linear3);
        this._drawer_linear4 = (LinearLayout) _nav_view.findViewById(R.id.linear4);
        this._drawer_linear5 = (LinearLayout) _nav_view.findViewById(R.id.linear5);
        this._drawer_textview3 = (TextView) _nav_view.findViewById(R.id.textview3);
        this._drawer_textview1 = (TextView) _nav_view.findViewById(R.id.textview1);
        this._drawer_textview12 = (TextView) _nav_view.findViewById(R.id.textview12);
        this._drawer_textview2 = (TextView) _nav_view.findViewById(R.id.textview2);
        this._drawer_imageview9 = (ImageView) _nav_view.findViewById(R.id.imageview9);
        this._drawer_textview4 = (TextView) _nav_view.findViewById(R.id.textview4);
        this._drawer_imageview10 = (ImageView) _nav_view.findViewById(R.id.imageview10);
        this._drawer_textview5 = (TextView) _nav_view.findViewById(R.id.textview5);
        this._drawer_imageview11 = (ImageView) _nav_view.findViewById(R.id.imageview11);
        this._drawer_textview6 = (TextView) _nav_view.findViewById(R.id.textview6);
        this._drawer_imageview12 = (ImageView) _nav_view.findViewById(R.id.imageview12);
        this._drawer_textview7 = (TextView) _nav_view.findViewById(R.id.textview7);
        this._drawer_imageview13 = (ImageView) _nav_view.findViewById(R.id.imageview13);
        this._drawer_textview8 = (TextView) _nav_view.findViewById(R.id.textview8);
        this._drawer_imageview14 = (ImageView) _nav_view.findViewById(R.id.imageview14);
        this._drawer_textview9 = (TextView) _nav_view.findViewById(R.id.textview9);
        this._drawer_imageview15 = (ImageView) _nav_view.findViewById(R.id.imageview15);
        this._drawer_textview10 = (TextView) _nav_view.findViewById(R.id.textview10);
        this._drawer_imageview16 = (ImageView) _nav_view.findViewById(R.id.imageview16);
        this._drawer_textview11 = (TextView) _nav_view.findViewById(R.id.textview11);
        this.admin = getSharedPreferences("admin", 0);
        this.auth = FirebaseAuth.getInstance();
        this.user = getSharedPreferences("user", 0);
		this.button_fatafat.setOnClickListener(new View.OnClickListener() {
			public void onClick(View param1View) {
				MainActivity.this.game_avail.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						listmap_game_avail = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								MainActivity.this.listmap_game_avail.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						if (((HashMap)MainActivity.this.listmap_game_avail.get(0)).get("ff").toString().equals("on")) {
							MainActivity.this.home_navigators.setClass(MainActivity.this.getApplicationContext(), UserChooseActivity.class);
							MainActivity.this.home_navigators.putExtra("Game", "Fatafat");
							MainActivity.this.startActivity(MainActivity.this.home_navigators);
							return;
						}
						SketchwareUtil.showMessage(MainActivity.this.getApplicationContext(), "Game Is Not Available Now");
					}
				});
			}
		});
		this.button_goa_kalyan.setOnClickListener(new View.OnClickListener() {
			public void onClick(View param1View) {
				MainActivity.this.game_avail.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						listmap_game_avail = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								MainActivity.this.listmap_game_avail.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						if (((HashMap)MainActivity.this.listmap_game_avail.get(0)).get("gk").toString().equals("on")) {
							MainActivity.this.home_navigators.setClass(MainActivity.this.getApplicationContext(), UserChooseActivity.class);
							MainActivity.this.home_navigators.putExtra("Game", "SILIGURI");
							MainActivity.this.startActivity(MainActivity.this.home_navigators);
							return;
						}
						SketchwareUtil.showMessage(MainActivity.this.getApplicationContext(), "Game Is Not Available Now");
					}
				});
			}
		});
		this.button_main_bazar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View param1View) {
				MainActivity.this.game_avail.addListenerForSingleValueEvent(new ValueEventListener() {
					public void onCancelled(DatabaseError param2DatabaseError) {}

					public void onDataChange(DataSnapshot param2DataSnapshot) {
						listmap_game_avail = new ArrayList();
						try {
							GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

							};
							Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
							while (iterator.hasNext()) {
								HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
								MainActivity.this.listmap_game_avail.add(hashMap);
							}
						} catch (Exception exception) {
							exception.printStackTrace();
						}
						if (((HashMap)MainActivity.this.listmap_game_avail.get(0)).get("mb").toString().equals("on")) {
							MainActivity.this.home_navigators.setClass(MainActivity.this.getApplicationContext(), UserChooseActivity.class);
							MainActivity.this.home_navigators.putExtra("Game", "Main Bazar");
							MainActivity.this.startActivity(MainActivity.this.home_navigators);
							return;
						}
						SketchwareUtil.showMessage(MainActivity.this.getApplicationContext(), "Game Is Not Available Now");
					}
				});
			}
		});
		this.button_result_fatafat.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass5 */

            public void onClick(View _view) {
                MainActivity.this.home_navigators.setClass(MainActivity.this.getApplicationContext(), UserChooseActivity.class);
                MainActivity.this.home_navigators.putExtra("Game", "Fatafat_r");
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.home_navigators);
            }
        });
        this.button_result_goa_kalyan.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass6 */

            public void onClick(View _view) {
                MainActivity.this.home_navigators.setClass(MainActivity.this.getApplicationContext(), UserChooseActivity.class);
                MainActivity.this.home_navigators.putExtra("Game", "SILIGURI_r");
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.home_navigators);
            }
        });
        this.button_result_main_bazar.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass7 */

            public void onClick(View _view) {
                MainActivity.this.home_navigators.setClass(MainActivity.this.getApplicationContext(), UserChooseActivity.class);
                MainActivity.this.home_navigators.putExtra("Game", "Main_Bazar_r");
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.home_navigators);
            }
        });
        this.button_add_money.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass8 */

            public void onClick(View _view) {
                MainActivity.this.drawer_navigators.setClass(MainActivity.this.getApplicationContext(), RequestMoneyActivity.class);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.drawer_navigators);
            }
        });
        this.button_withdraw.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass9 */

            public void onClick(View _view) {
                MainActivity.this.home_navigators.setClass(MainActivity.this.getApplicationContext(), WithdrawMoneyActivity.class);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.home_navigators);
            }
        });
        this.button1.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass10 */

            public void onClick(View _view) {
                MainActivity.this.home_navigators.setClass(MainActivity.this.getApplicationContext(), TransactionHistoryActivity.class);
                MainActivity.this.home_navigators.putExtra("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.home_navigators);
            }
        });
        this.button2.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass11 */

            public void onClick(View _view) {
                MainActivity.this.home_navigators.setClass(MainActivity.this.getApplicationContext(), PlayHistoryActivity.class);
                MainActivity.this.home_navigators.putExtra("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.home_navigators);
            }
        });
        this.linear_call_us.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass12 */

            public void onClick(View _view) {
                MainActivity.this.call.setAction("android.intent.action.DIAL");
                MainActivity.this.call.setData(Uri.parse("tel:+918697512205"));
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.call);
            }
        });
        this.linear_whatsapp.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass13 */

            public void onClick(View _view) {
                MainActivity.this.wp.setAction("android.intent.action.VIEW");
                MainActivity.this.wp.setData(Uri.parse("https://api.whatsapp.com/send?phone=+918697512205"));
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.wp);
            }
        });
        this._fab.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass14 */

            public void onClick(View _view) {
                MainActivity.this.i.setClass(MainActivity.this.getApplicationContext(), AdminActivity.class);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.i);
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
		this._game_avail_child_listener = childEventListener;
		this.game_avail.addChildEventListener(childEventListener);
		this._drawer_linear_home.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass17 */

            public void onClick(View _view) {
                MainActivity.this._drawer.closeDrawer(GravityCompat.START);
            }
        });
        this._drawer_linear_request_money.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass18 */

            public void onClick(View _view) {
                MainActivity.this._drawer.closeDrawer(GravityCompat.START);
                MainActivity.this.drawer_navigators.setClass(MainActivity.this.getApplicationContext(), RequestMoneyActivity.class);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.drawer_navigators);
            }
        });
        this._drawer_linear_withdraw.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass19 */

            public void onClick(View _view) {
                MainActivity.this._drawer.closeDrawer(GravityCompat.START);
                MainActivity.this.drawer_navigators.setClass(MainActivity.this.getApplicationContext(), WithdrawMoneyActivity.class);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.drawer_navigators);
            }
        });
        this._drawer_linear_my_acc.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass20 */

            public void onClick(View _view) {
                MainActivity.this._drawer.closeDrawer(GravityCompat.START);
                MainActivity.this.drawer_navigators.setClass(MainActivity.this.getApplicationContext(), MyAccountActivity.class);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.drawer_navigators);
            }
        });
        this._drawer_linear_g_time.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass21 */

            public void onClick(View _view) {
                MainActivity.this._drawer.closeDrawer(GravityCompat.START);
                MainActivity.this.drawer_navigators.setClass(MainActivity.this.getApplicationContext(), GamesTimeActivity.class);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.drawer_navigators);
            }
        });
        this._drawer_linear_rules.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass22 */

            public void onClick(View _view) {
                MainActivity.this._drawer.closeDrawer(GravityCompat.START);
                MainActivity.this.drawer_navigators.setClass(MainActivity.this.getApplicationContext(), GameRulesActivity.class);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.drawer_navigators);
            }
        });
        this._drawer_linear_share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                MainActivity.this.game_avail.addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onCancelled(DatabaseError param2DatabaseError) {}

                    public void onDataChange(DataSnapshot param2DataSnapshot) {
                        listmap_game_avail = new ArrayList();
                        try {
                            GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

                            };
                            Iterator<DataSnapshot> iterator = param2DataSnapshot.getChildren().iterator();
                            while (iterator.hasNext()) {
                                HashMap hashMap = (HashMap)((DataSnapshot)iterator.next()).getValue(genericTypeIndicator);
                                MainActivity.this.listmap_game_avail.add(hashMap);
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        MainActivity.this._drawer.closeDrawer(GravityCompat.START);
                        a = "Sharing Game and Play App";
                        b = "Hi, join Game Play and earn money by just playing. Easy & fast!. Download the app at \n ".concat(((HashMap)MainActivity.this.listmap_game_avail.get(0)).get("link").toString());
                        Intent intent = new Intent("android.intent.action.SEND");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.SUBJECT", MainActivity.this.a);
                        intent.putExtra("android.intent.extra.TEXT", MainActivity.this.b);
                        MainActivity.this.startActivity(Intent.createChooser(intent, "Share using"));
                    }
                });
            }
        });
        this._drawer_linear_logout.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.MainActivity.AnonymousClass24 */

            public void onClick(View _view) {
                MainActivity.this._drawer.closeDrawer(GravityCompat.START);
                FirebaseAuth.getInstance().signOut();
                MainActivity.this.drawer_navigators.setClass(MainActivity.this.getApplicationContext(), SigninActivity.class);
                MainActivity mainActivity = MainActivity.this;
                mainActivity.startActivity(mainActivity.drawer_navigators);
            }
        });
        this.auth_updateEmailListener = new OnCompleteListener<Void>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass25 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> _param1) {
                _param1.isSuccessful();
                if (_param1.getException() != null) {
                    _param1.getException().getMessage();
                }
            }
        };
        this.auth_updatePasswordListener = new OnCompleteListener<Void>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass26 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> _param1) {
                _param1.isSuccessful();
                if (_param1.getException() != null) {
                    _param1.getException().getMessage();
                }
            }
        };
        this.auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass27 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> _param1) {
                _param1.isSuccessful();
                if (_param1.getException() != null) {
                    _param1.getException().getMessage();
                }
            }
        };
        this.auth_deleteUserListener = new OnCompleteListener<Void>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass28 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> _param1) {
                _param1.isSuccessful();
                if (_param1.getException() != null) {
                    _param1.getException().getMessage();
                }
            }
        };
        this.auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass29 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this.auth_updateProfileListener = new OnCompleteListener<Void>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass30 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> _param1) {
                _param1.isSuccessful();
                if (_param1.getException() != null) {
                    _param1.getException().getMessage();
                }
            }
        };
        this.auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass31 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> task) {
                task.isSuccessful();
                if (task.getException() != null) {
                    task.getException().getMessage();
                }
            }
        };
        this._auth_create_user_listener = new OnCompleteListener<AuthResult>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass32 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> _param1) {
                _param1.isSuccessful();
                if (_param1.getException() != null) {
                    _param1.getException().getMessage();
                }
            }
        };
        this._auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass33 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<AuthResult> _param1) {
                _param1.isSuccessful();
                if (_param1.getException() != null) {
                    _param1.getException().getMessage();
                }
            }
        };
        this._auth_reset_password_listener = new OnCompleteListener<Void>() {
            /* class dream.fata.fat.MainActivity.AnonymousClass34 */

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task<Void> _param1) {
                _param1.isSuccessful();
            }
        };
    }

    private void initializeLogic() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.pd = progressDialog;
        progressDialog.setMessage("Loading");
        this.pd.setCancelable(false);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            this._drawer_textview1.setText(this.user.getString("name", ""));
            this._drawer_textview3.setText(this.user.getString("number", ""));
            this.button_fatafat.setElevation(30.0f);
            this.button_fatafat.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass35 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -49023));
            this.button_goa_kalyan.setElevation(30.0f);
            this.button_goa_kalyan.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass36 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -49023));
            this.button_main_bazar.setElevation(30.0f);
            this.button_main_bazar.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass37 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -49023));
            this.button_result_fatafat.setElevation(30.0f);
            this.button_result_fatafat.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass38 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -5317));
            this.button_result_goa_kalyan.setElevation(30.0f);
            this.button_result_goa_kalyan.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass39 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -5317));
            this.button_result_main_bazar.setElevation(30.0f);
            this.button_result_main_bazar.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass40 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -5317));
            this.button_add_money.setElevation(30.0f);
            this.button_add_money.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass41 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -16728876));
            this.button_withdraw.setElevation(30.0f);
            this.button_withdraw.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass42 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -16728876));
            this.linear_play_games.setElevation(30.0f);
            this.linear_play_games.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass43 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(20, 1, -10453621, -1));
            this.linear_results.setElevation(30.0f);
            this.linear_results.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass44 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(20, 1, -10453621, -1));
            this.linear_act.setElevation(30.0f);
            this.linear_act.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass45 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(20, 1, -10453621, -1));
            this.button1.setElevation(30.0f);
            this.button1.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass46 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -1499549));
            this.button2.setElevation(30.0f);
            this.button2.setBackground(new GradientDrawable() {
                /* class dream.fata.fat.MainActivity.AnonymousClass47 */

                public GradientDrawable getIns(int a, int b, int c, int d) {
                    setCornerRadius((float) a);
                    setStroke(b, c);
                    setColor(d);
                    return this;
                }
            }.getIns(90, 0, -1, -14575885));
        } else {
            this.i.setClass(getApplicationContext(), SigninActivity.class);
            startActivity(this.i);
        }
        if (this.admin.getString("admin", "").equals("true")) {
            this._fab.show();
        } else {
            this._fab.hide();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            this.pd.show();
            this.path = "users/".concat(FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(" @", "").replace(".", "").replace(" ", ""));
            this.users.removeEventListener(this._users_child_listener);
            DatabaseReference databaseReference = this._firebase.getReference(this.path);
            this.users = databaseReference;
            databaseReference.addChildEventListener(this._users_child_listener);
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
                            MainActivity.this.listmap.add(hashMap);
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    MainActivity.this.user.edit().putString("name", ((HashMap)MainActivity.this.listmap.get(0)).get("name").toString()).commit();
                    MainActivity.this.user.edit().putString("email", ((HashMap)MainActivity.this.listmap.get(0)).get("email").toString()).commit();
                    MainActivity.this.user.edit().putString("number", ((HashMap)MainActivity.this.listmap.get(0)).get("number").toString()).commit();
                    MainActivity.this.user.edit().putString("balance", ((HashMap)MainActivity.this.listmap.get(0)).get("balance").toString()).commit();
                    MainActivity.this._drawer_textview12.setText("₹ ".concat(((HashMap)MainActivity.this.listmap.get(0)).get("balance").toString()));
                    MainActivity.this.pd.dismiss();
                }
            });
        }
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        finishAffinity();
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
