package com.thunderclouddev.tirforgoodreads.ui.viewbooks

import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.thunderclouddev.goodreadsapisdk.model.Book
import com.thunderclouddev.tirforgoodreads.R
import com.thunderclouddev.tirforgoodreads.databinding.BookItemBinding
import com.thunderclouddev.tirforgoodreads.getOrDefaultIfNullOrBlank
import com.thunderclouddev.tirforgoodreads.ui.BaseRecyclerViewAdapter
import com.thunderclouddev.tirforgoodreads.ui.SortedListAdapter

/**
 * @author David Whitman on 11 Mar, 2017.
 */
class ViewBooksAdapter
    : SortedListAdapter<ViewBooksAdapter.BookViewModel>(BookViewModel::class.java, BookViewModel.DefaultComparator()) {

    override fun areItemsTheSame(item1: BookViewModel, item2: BookViewModel) = item1.book.id == item2.book.id

    override fun areItemContentsTheSame(oldItem: BookViewModel, newItem: BookViewModel) = oldItem.book == newItem.book

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int) =
            ViewHolder(DataBindingUtil.inflate<BookItemBinding>(inflater, R.layout.book_item, parent, false))

    override fun getItemCount() = items().size


    data class BookViewModel(val book: Book) : BaseRecyclerViewAdapter.ViewModel {
        val title = book.titleWithoutSeries.getOrDefaultIfNullOrBlank(book.title)
        val authors = book.authors.joinToString { it.name }
        val imageUrl = book.imageUrl

        class DefaultComparator : Comparator<BookViewModel> {
            override fun compare(left: BookViewModel, right: BookViewModel): Int {
                return left.book.title.compareTo(right.book.title) // TODO: Handle when this is blank
            }
        }
    }

    data class ViewHolder(private val binding: BookItemBinding)
        : BaseRecyclerViewAdapter.ViewHolder<BookViewModel>(binding) {
        override fun performBind(viewModel: BookViewModel) {
            Picasso.with(binding.root.context)
                    .load(viewModel.imageUrl)
                    .into(binding.bookCover)

            binding.book = viewModel
        }
    }
}