package laboral.firetg.control.adp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


import laboral.firetg.R;
import laboral.firetg.model.aluno;

public class AlunoAdapter extends ArrayAdapter<aluno> {
    public AlunoAdapter(Context context, ArrayList<aluno> alunos){
        super(context,0,alunos);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        // Get the data item for this position

        aluno alu = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_aluno, parent, false);

        }

        // Lookup view for data population

        TextView jtxtNome = (TextView) convertView.findViewById(R.id.txtNome);

        TextView jtxtObs = (TextView) convertView.findViewById(R.id.txtObs);

        // Populate the data into the template view using the data object

        jtxtNome.setText(alu.getNome());

        jtxtObs.setText(alu.getObservacao());

        // Return the completed view to render on screen

        return convertView;
    }

}
