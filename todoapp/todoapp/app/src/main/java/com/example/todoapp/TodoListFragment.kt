package com.example.todoapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.example.todoapp.databinding.FragmentTodoListBinding
import java.util.logging.Logger

class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!
    private lateinit var todoAdapter : TodoAdapter

    private val todoViewModel: TodoViewModel by viewModels { TodoViewModelFactory(requireActivity().application)}

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoAdapter = TodoAdapter {
            Log.d("Click2", it.toString())
            val action = TodoListFragmentDirections.actionFragmentTodoListToFragmentEditTodo(it.id)
            this.findNavController().navigate(action)
        }
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.todoRecyclerView.adapter = todoAdapter

        todoViewModel.todoList.observe(this.viewLifecycleOwner) { items ->
            items.let {
                todoAdapter.submitList(it)
                Log.d("Log", it.toString())
            }
        }

        binding.fabAddTodo.setOnClickListener {
            findNavController().navigate(R.id.action_todoListFragment_to_addTodoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}