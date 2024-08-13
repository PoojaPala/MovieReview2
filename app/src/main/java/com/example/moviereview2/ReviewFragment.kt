package com.example.moviereview2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReviewFragment : Fragment() {

    private lateinit var reviewRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reviewRecyclerView = view.findViewById(R.id.reviewRecyclerView)
        reviewRecyclerView.layoutManager = LinearLayoutManager(activity)

        val reviews = listOf(
            "An absolute masterpiece!",
            "A thrilling journey from start to finish.",
            "The visuals were stunning, a must-see in theaters.",
            "A gripping storyline with exceptional performances.",
            "Exceeded all expectations, highly recommend!",
            "A beautifully crafted film with heart and soul.",
            "A cinematic experience like no other.",
            "Emotionally powerful and incredibly moving.",
            "An unforgettable performance by the lead actor.",
            "A perfect blend of action, drama, and suspense.",
            "A rollercoaster of emotions, brilliantly executed.",
            "A true work of art, deserving of all the praise.",
            "An inspiring story with a powerful message.",
            "A must-watch for fans of the genre.",
            "A timeless classic that will be remembered for years."
        )
        val adapter = ReviewAdapter(reviews)
        reviewRecyclerView.adapter = adapter
    }
}
