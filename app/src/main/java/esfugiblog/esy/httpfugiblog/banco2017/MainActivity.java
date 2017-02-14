package esfugiblog.esy.httpfugiblog.banco2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etID;
    private EditText etNome;
    private EditText etCurso;
    private TextView tvResultado;
    private AlunosService alunosService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etID=(EditText) findViewById(R.id.etID);
        etNome=(EditText) findViewById(R.id.etNome);
        etCurso=(EditText) findViewById(R.id.etCurso);
        tvResultado=(TextView) findViewById(R.id.tvResultado);
        alunosService = new AlunosService(getBaseContext());

    }

    public void salvar(View view) {
        Toast.makeText(getApplicationContext(), "Salvando", Toast.LENGTH_SHORT).show();

            Aluno al = new Aluno();
            if (!etID.getText().toString().isEmpty()){
                al.setId(Integer.parseInt(etID.getText().toString()));
            }
            al.setNome(etNome.getText().toString());
            al.setCurso(etCurso.getText().toString());
            alunosService.salvar(al);
            etID.setText("");
            etCurso.setText("");
            etNome.setText("");



    }

    public void excluir(View view) {
        Toast.makeText(getApplicationContext(), "Excluindo", Toast.LENGTH_SHORT).show();

        alunosService.remover(Integer.parseInt(etID.getText().toString()));
    }


    public void listar(View view) {
        Toast.makeText(getApplicationContext(), "Listando", Toast.LENGTH_SHORT).show();

        List<Aluno>alunos= alunosService.buscar();
        tvResultado.setText(alunos.toString());
    }


    public void carregar(View view) {
        Aluno al=alunosService.buscar(Integer.parseInt(etID.getText().toString()));
        if (al==null){
            Toast.makeText(getApplicationContext(), "Não Existe", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Carregando", Toast.LENGTH_SHORT).show();
        }
        etCurso.setText(al.getCurso());
        etNome.setText(al.getNome());
    }
}
