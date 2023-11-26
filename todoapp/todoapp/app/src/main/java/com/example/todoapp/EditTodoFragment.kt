package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.TodoEntity
import com.example.todoapp.databinding.FragmentEditTodoBinding

class EditTodoFragment : Fragment() {

    private var _binding: FragmentEditTodoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory(requireActivity().application)
    }

    private var todoId: Long = -1L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            todoId = it.getLong(ARG_TODO_ID, -1L)
        }

        if (todoId == -1L) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        } else {
            val onBackPressedCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)

            viewModel.todoList.observe(viewLifecycleOwner) { todos ->
                val todo = todos.find { it.id == todoId }
                todo?.let {
                    binding.titleEditText.setText(it.title)
                    binding.descriptionEditText.setText(it.description)

                    binding.updateButton.setOnClickListener {
                        val updatedTitle = binding.titleEditText.text.toString()
                        val updatedDescription = binding.descriptionEditText.text.toString()

                        if (updatedTitle.isNotEmpty() && updatedDescription.isNotEmpty()) {
                            val updatedTodo = todo.copy(
                                title = updatedTitle,
                                description = updatedDescription
                            )
                            viewModel.updateTodo(updatedTodo)

                            requireActivity().onBackPressedDispatcher.onBackPressed()
                        }
                    }
                } ?: run {
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_TODO_ID = "todo_id"

        fun newInstance(todoId: Long): EditTodoFragment {
            val fragment = EditTodoFragment()
            val args = Bundle()
            args.putLong(ARG_TODO_ID, todoId)
            fragment.arguments = args
            return fragment
        }
    }
}