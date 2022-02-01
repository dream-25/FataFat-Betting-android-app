package dream.fata.fat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AdminActivity extends AppCompatActivity {
    private AppBarLayout _app_bar;
    private CoordinatorLayout _coordinator;
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private ChildEventListener _game_avail_child_listener;
    private ChildEventListener _rr_links_child_listener;
    private Toolbar _toolbar;
    private Button button1;
    private Button button_del;
    private Button button_fata_fat;
    private Button button_ff_kolkata;
    private Button button_main_bazar;
    private Button ff_online;
    private DatabaseReference game_avail = this._firebase.getReference("game_avail");
    private Button gk_online;
    private Intent i = new Intent();
    private Intent ii = new Intent();
    private LinearLayout linear1;
    private LinearLayout linear10;
    private LinearLayout linear12;
    private LinearLayout linear13;
    private LinearLayout linear14;
    private LinearLayout linear22;
    private LinearLayout linear26;
    private LinearLayout linear27;
    private LinearLayout linear3;
    private LinearLayout linear7;
    private LinearLayout linear8;
    private LinearLayout linear9;
    private ArrayList<HashMap<String, Object>> listmap_game_avail = new ArrayList<>();
    private HashMap<String, Object> map_game_avail = new HashMap<>();
    private Button mb_online;
    private ProgressDialog pd;
    private DatabaseReference rr_links = this._firebase.getReference("rr_links");
    private HashMap<String, Object> rr_links_m = new HashMap<>();
    private ArrayList<HashMap<String, Object>> rr_listmap = new ArrayList<>();
    private Switch switch1;
    private Switch switch2;
    private Switch switch3;
    private TextView textview3;
    private TextView textview5;
    private ScrollView vscroll1;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.admin);
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
            /* class dream.fata.fat.AdminActivity.AnonymousClass1 */

            public void onClick(View _v) {
                AdminActivity.this.onBackPressed();
            }
        });
        this.linear1 = (LinearLayout) findViewById(R.id.linear1);
        this.linear3 = (LinearLayout) findViewById(R.id.linear3);
        this.vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
        this.linear7 = (LinearLayout) findViewById(R.id.linear7);
        this.linear27 = (LinearLayout) findViewById(R.id.linear27);
        this.linear8 = (LinearLayout) findViewById(R.id.linear8);
        this.linear9 = (LinearLayout) findViewById(R.id.linear9);
        this.linear12 = (LinearLayout) findViewById(R.id.linear12);
        this.linear22 = (LinearLayout) findViewById(R.id.linear22);
        this.linear26 = (LinearLayout) findViewById(R.id.linear26);
        this.switch1 = (Switch) findViewById(R.id.switch1);
        this.switch2 = (Switch) findViewById(R.id.switch2);
        this.switch3 = (Switch) findViewById(R.id.switch3);
        this.textview3 = (TextView) findViewById(R.id.textview3);
        this.linear10 = (LinearLayout) findViewById(R.id.linear10);
        this.button_fata_fat = (Button) findViewById(R.id.button_fata_fat);
        this.linear13 = (LinearLayout) findViewById(R.id.linear13);
        this.linear14 = (LinearLayout) findViewById(R.id.linear14);
        this.button_ff_kolkata = (Button) findViewById(R.id.button_ff_kolkata);
        this.button_main_bazar = (Button) findViewById(R.id.button_main_bazar);
        this.textview5 = (TextView) findViewById(R.id.textview5);
        this.button1 = (Button) findViewById(R.id.button1);
        this.button_del = (Button) findViewById(R.id.button_del);
        this.ff_online = (Button) findViewById(R.id.ff_online);
        this.gk_online = (Button) findViewById(R.id.gk_online);
        this.mb_online = (Button) findViewById(R.id.mb_online);



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
                AdminActivity.this.pd.dismiss();
            }

            public void onChildMoved(DataSnapshot param1DataSnapshot, String param1String) {}

            public void onChildRemoved(DataSnapshot param1DataSnapshot) {
                GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator = new GenericTypeIndicator<HashMap<String, Object>>() {

                };
                param1DataSnapshot.getKey();
                HashMap hashMap = (HashMap)param1DataSnapshot.getValue(genericTypeIndicator);
            }
        };
        this._rr_links_child_listener = childEventListener;
        this.rr_links.addChildEventListener(childEventListener);





        this.ff_online.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass3 */

            public void onClick(View _view) {
                final FlatDialog flatDialog = new FlatDialog(AdminActivity.this);
                flatDialog.setTitle("Update Link").setSubtitle("Enter Your Link").setFirstTextFieldHint("Link").setFirstButtonText("Update").setSecondButtonText("Cancel").withFirstButtonListner(new View.OnClickListener() {
                    /* class dream.fata.fat.AdminActivity.AnonymousClass3.AnonymousClass2 */

                    public void onClick(View view) {
                        AdminActivity.this.rr_links_m = new HashMap();
                        AdminActivity.this.rr_links_m.put("ff_online", flatDialog.getFirstTextField());
                        AdminActivity.this.rr_links.child("links").updateChildren(AdminActivity.this.rr_links_m);
                        AdminActivity.this.pd.show();
                        flatDialog.dismiss();
                    }
                }).withSecondButtonListner(new View.OnClickListener() {
                    /* class dream.fata.fat.AdminActivity.AnonymousClass3.AnonymousClass1 */

                    public void onClick(View view) {
                        flatDialog.dismiss();
                    }
                }).show();
            }
        });
        this.gk_online.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass4 */

            public void onClick(View _view) {
                final FlatDialog flatDialog = new FlatDialog(AdminActivity.this);
                flatDialog.setTitle("Update Link").setSubtitle("Enter Your Link").setFirstTextFieldHint("Link").setFirstButtonText("Update").setSecondButtonText("Cancel").withFirstButtonListner(new View.OnClickListener() {
                    /* class dream.fata.fat.AdminActivity.AnonymousClass4.AnonymousClass2 */

                    public void onClick(View view) {
                        AdminActivity.this.rr_links_m = new HashMap();
                        AdminActivity.this.rr_links_m.put("gk_online", flatDialog.getFirstTextField());
                        AdminActivity.this.rr_links.child("links").updateChildren(AdminActivity.this.rr_links_m);
                        AdminActivity.this.pd.show();
                        flatDialog.dismiss();
                    }
                }).withSecondButtonListner(new View.OnClickListener() {
                    /* class dream.fata.fat.AdminActivity.AnonymousClass4.AnonymousClass1 */

                    public void onClick(View view) {
                        flatDialog.dismiss();
                    }
                }).show();
            }
        });
        this.mb_online.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass5 */

            public void onClick(View _view) {
                final FlatDialog flatDialog = new FlatDialog(AdminActivity.this);
                flatDialog.setTitle("Update Link").setSubtitle("Enter Your Link").setFirstTextFieldHint("Link").setFirstButtonText("Update").setSecondButtonText("Cancel").withFirstButtonListner(new View.OnClickListener() {
                    /* class dream.fata.fat.AdminActivity.AnonymousClass5.AnonymousClass2 */

                    public void onClick(View view) {
                        AdminActivity.this.rr_links_m = new HashMap();
                        AdminActivity.this.rr_links_m.put("mb_online", flatDialog.getFirstTextField());
                        AdminActivity.this.rr_links.child("links").updateChildren(AdminActivity.this.rr_links_m);
                        AdminActivity.this.pd.show();
                        flatDialog.dismiss();
                    }
                }).withSecondButtonListner(new View.OnClickListener() {
                    /* class dream.fata.fat.AdminActivity.AnonymousClass5.AnonymousClass1 */

                    public void onClick(View view) {
                        flatDialog.dismiss();
                    }
                }).show();
            }
        });
        this.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass6 */

            public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
                if (_param2) {
                    AdminActivity.this.map_game_avail = new HashMap();
                    AdminActivity.this.map_game_avail.put("ff", "on");
                } else {
                    AdminActivity.this.map_game_avail = new HashMap();
                    AdminActivity.this.map_game_avail.put("ff", "off");
                }
                AdminActivity.this.game_avail.child("game").updateChildren(AdminActivity.this.map_game_avail);
            }
        });
        this.switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass7 */

            public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
                if (_param2) {
                    AdminActivity.this.map_game_avail = new HashMap();
                    AdminActivity.this.map_game_avail.put("gk", "on");
                } else {
                    AdminActivity.this.map_game_avail = new HashMap();
                    AdminActivity.this.map_game_avail.put("gk", "off");
                }
                AdminActivity.this.game_avail.child("game").updateChildren(AdminActivity.this.map_game_avail);
            }
        });
        this.switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass8 */

            public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
                if (_param2) {
                    AdminActivity.this.map_game_avail = new HashMap();
                    AdminActivity.this.map_game_avail.put("mb", "on");
                } else {
                    AdminActivity.this.map_game_avail = new HashMap();
                    AdminActivity.this.map_game_avail.put("mb", "off");
                }
                AdminActivity.this.game_avail.child("game").updateChildren(AdminActivity.this.map_game_avail);
            }
        });
        this.button_fata_fat.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass9 */

            public void onClick(View _view) {
                AdminActivity.this.i.setClass(AdminActivity.this.getApplicationContext(), ChooseActivity.class);
                AdminActivity.this.i.putExtra("Game", "Fatafat");
                AdminActivity adminActivity = AdminActivity.this;
                adminActivity.startActivity(adminActivity.i);
            }
        });
        this.button_ff_kolkata.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass10 */

            public void onClick(View _view) {
                AdminActivity.this.i.setClass(AdminActivity.this.getApplicationContext(), ChooseActivity.class);
                AdminActivity.this.i.putExtra("Game", "SILIGURI");
                AdminActivity adminActivity = AdminActivity.this;
                adminActivity.startActivity(adminActivity.i);
            }
        });
        this.button_main_bazar.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass11 */

            public void onClick(View _view) {
                AdminActivity.this.i.setClass(AdminActivity.this.getApplicationContext(), ChooseActivity.class);
                AdminActivity.this.i.putExtra("Game", "Main Bazar");
                AdminActivity adminActivity = AdminActivity.this;
                adminActivity.startActivity(adminActivity.i);
            }
        });
        this.button1.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass12 */

            public void onClick(View _view) {
                AdminActivity.this.ii.setClass(AdminActivity.this.getApplicationContext(), UsersActivity.class);
                AdminActivity adminActivity = AdminActivity.this;
                adminActivity.startActivity(adminActivity.ii);
            }
        });



        this.button_del.setOnClickListener(new View.OnClickListener() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass12 */

            public void onClick(View _view) {
             game_avail.removeEventListener(_game_avail_child_listener);
             game_avail = _firebase.getReference("/");
             game_avail.addChildEventListener(_game_avail_child_listener);
             game_avail.child("ff_results").removeValue();
             game_avail.child("userHistory").removeValue();
             SketchwareUtil.showMessage(getApplicationContext(), "Users History & Results are deleted");
                game_avail.removeEventListener(_game_avail_child_listener);
                game_avail = _firebase.getReference("game_avail");
                game_avail.addChildEventListener(_game_avail_child_listener);
            }
        });





        _game_avail_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                SketchwareUtil.showMessage(getApplicationContext(), "Updated");
            }

            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {

            }

            @Override
            public void onChildRemoved(DataSnapshot _param1) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onCancelled(DatabaseError _param1) {
                final int _errorCode = _param1.getCode();
                final String _errorMessage = _param1.getMessage();

            }
        };
        game_avail.addChildEventListener(_game_avail_child_listener);




    }

    private void initializeLogic() {
        this.button_fata_fat.setElevation(30.0f);
        this.button_fata_fat.setBackground(new GradientDrawable() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass14 */

            public GradientDrawable getIns(int a, int b, int c, int d) {
                setCornerRadius((float) a);
                setStroke(b, c);
                setColor(d);
                return this;
            }
        }.getIns(20, 0, -1, -1499549));
        this.button_ff_kolkata.setElevation(30.0f);
        this.button_ff_kolkata.setBackground(new GradientDrawable() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass15 */

            public GradientDrawable getIns(int a, int b, int c, int d) {
                setCornerRadius((float) a);
                setStroke(b, c);
                setColor(d);
                return this;
            }
        }.getIns(20, 0, -1, -1499549));
        this.button_main_bazar.setElevation(30.0f);
        this.button_main_bazar.setBackground(new GradientDrawable() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass16 */

            public GradientDrawable getIns(int a, int b, int c, int d) {
                setCornerRadius((float) a);
                setStroke(b, c);
                setColor(d);
                return this;
            }
        }.getIns(20, 0, -1, -1499549));
        this.button1.setElevation(30.0f);
        this.button1.setBackground(new GradientDrawable() {
            /* class dream.fata.fat.AdminActivity.AnonymousClass17 */

            public GradientDrawable getIns(int a, int b, int c, int d) {
                setCornerRadius((float) a);
                setStroke(b, c);
                setColor(d);
                return this;
            }
        }.getIns(20, 0, -1, -28928));
        ProgressDialog progressDialog = new ProgressDialog(this);
        this.pd = progressDialog;
        progressDialog.setMessage("Loading");
        this.pd.setCancelable(false);
        this.pd.show();
        game_avail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot _dataSnapshot) {
                listmap_game_avail = new ArrayList<>();
                try {
                    GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                    for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                        HashMap<String, Object> _map = _data.getValue(_ind);
                        listmap_game_avail.add(_map);
                    }
                }
                catch (Exception _e) {
                    _e.printStackTrace();
                }
                if (listmap_game_avail.get((int)0).get("ff").toString().equals("on")) {
                    switch1.setChecked(true);
                }
                else {
                    switch1.setChecked(false);
                }
                if (listmap_game_avail.get((int)0).get("gk").toString().equals("on")) {
                    switch2.setChecked(true);
                }
                else {
                    switch2.setChecked(false);
                }
                if (listmap_game_avail.get((int)0).get("mb").toString().equals("on")) {
                    switch3.setChecked(true);
                }
                else {
                    switch3.setChecked(false);
                }
                pd.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError _databaseError) {
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
