package garcia.sergio.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import garcia.sergio.mydigimind.R
import garcia.sergio.mydigimind.ui.Task

class HomeFragment : Fragment() {



    private var adaptador: AdaptadorTareas? = null

    private lateinit var homeViewModel: HomeViewModel


    companion object{
        var tasks = ArrayList<Task>()
        var first = true
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?




    ): View? {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, {

        })

        if(first) {
            fillTasks()
            first = false
        }

        adaptador = AdaptadorTareas(root.context, tasks)

        val gridView : GridView = root.findViewById(R.id.gridView)

        gridView.adapter = adaptador


        return root
    }



    fun fillTasks(){
        tasks.add(Task("Practice 1", arrayListOf("Monday", "Sunday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday"), "11:30"))
        tasks.add(Task("Practice 3", arrayListOf("Thursday"), "15:30"))
        tasks.add(Task("Practice 4", arrayListOf("Friday"), "19:30"))
        tasks.add(Task("Practice 5", arrayListOf("Tuesday"), "20:30"))
        tasks.add(Task("Practice 6", arrayListOf("Saturday"), "17:00"))
        tasks.add(Task("Practice 7", arrayListOf("Wednesday"), "20:00"))

    }


    private class AdaptadorTareas : BaseAdapter{

        var tasks = ArrayList<Task>()
        var contexto: Context? = null

        constructor(contexto: Context, task: ArrayList<Task>){
            this.contexto = contexto
            this.tasks = task

        }

        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var task = tasks[position]
            var inflador = LayoutInflater.from(contexto)
            var vista = inflador.inflate(R.layout.task_view, null)

            var tv_title: TextView = vista.findViewById(R.id.tv_title)
            var tv_time: TextView = vista.findViewById(R.id.tv_time)
            var tv_day: TextView = vista.findViewById(R.id.tv_days)


            tv_title.setText(task.title)
            tv_time.setText(task.time)
            tv_day.setText(task.days.toString())

            return vista


        }
    }



}