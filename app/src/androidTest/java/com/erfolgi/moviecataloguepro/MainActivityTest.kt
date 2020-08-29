package com.erfolgi.moviecataloguepro

import android.view.View
import android.widget.TextView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.erfolgi.moviecataloguepro.ui.movie.MovieAdapter
import com.erfolgi.moviecataloguepro.ui.tvshow.TvShowAdapter
import com.erfolgi.moviecataloguepro.util.EspressoIdlingResource
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    /**
    *           [[Skenario Test]]
    * - Start
    * - Open Movie in 4th list
    * - Swipe up and Check release date
    * - Add to favorite
    * - Back
     *
    * - Move to TvShow Fragment
    * - Open TV Show in 4th list
    * - Swipe up and check overview
    * - Add to favorite
    * - Back
     *
    * - Move to Favorite Movie
    * - Check if added Movie Title exist
    * - Open added Movie
    * - Remove from Favorite
    * - Back
    * -  Check if removed Movie Title does not exist
     *
    * -  Move to Favorite TV Show
    * - Check if added TV Show Title exist
    * - Open added TV Show
    * - Remove from Favorite
    * - Back
    * -  Check if removed TV Show Title does not exist
    * - End
    **/
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun setup(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoIdlingResource)
    }

    @Test
    fun testAppBehaviour() {

        /**
            Movie List
        **/
        onView(withId(R.id.rv_movie))
            .perform(actionOnItemAtPosition<MovieAdapter.MovieViewHolder>(4, click()))

        /**
         *  Movie Detail
         */

        onView(withId(R.id.movie_detail_poster)).perform(ViewActions.swipeUp())
        onView(withId(R.id.movie_detail_backdrop)).perform(ViewActions.swipeUp())
        val movieTitle = getText(onView(withId(R.id.movie_detail_title)))
        onView(withId(R.id.movie_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.fab_movie)).perform(click())

        try{
            //if exist in room
            onView(withText("Removed from Favorite"))
                .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
            onView(withId(R.id.fab_movie)).perform(click())
        }catch (e:Exception){
        }

        onView(withText("Saved to Favorite"))
            .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
        Espresso.pressBack()

        /**
         *  Movie List
         */
        onView(withId(R.id.rv_movie)).perform(ViewActions.swipeUp())
        onView(withId(R.id.nav_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.navigation_dashboard)).perform(click())

        /**
         *  Tvshow List
         */
        onView(withId(R.id.rv_tv_show))
            .perform(actionOnItemAtPosition<TvShowAdapter.TvShowViewHolder>(4, click()))

        /**
         *  Tvshow Detail
         */
        onView(withId(R.id.tv_show_detail_poster)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tv_detail_backdrop)).perform(ViewActions.swipeUp())
        val tvTitle = getText(onView(withId(R.id.tv_show_detail_title)))
        onView(withId(R.id.tv_show_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_tv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.fab_tv)).perform(click())

        try{
            onView(withText("Removed from Favorite"))
                .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
                .check(matches(isDisplayed()))
            onView(withId(R.id.fab_tv)).perform(click())
        }catch (e:Exception){
        }

        onView(withText("Saved to Favorite"))
            .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
        Espresso.pressBack()

        /**
         *  Tvshow List
         */
        onView(withId(R.id.rv_tv_show)).perform(ViewActions.swipeUp())
        onView(withId(R.id.nav_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.navigation_favorite)).perform(click())

        /**
         *  Fav Movie List
         */
        onView(withText(movieTitle)).check(matches(isDisplayed()))
        onView(withText(movieTitle)).perform(click())

        /**
         *  Movie Detail
         */
        onView(withId(R.id.movie_detail_poster)).perform(ViewActions.swipeUp())
        onView(withId(R.id.movie_detail_backdrop)).perform(ViewActions.swipeUp())
        onView(withId(R.id.movie_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_movie))
            .check(matches(isDisplayed()))
        onView(withId(R.id.fab_movie)).perform(click())
        onView(withText("Removed from Favorite"))
            .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
        Espresso.pressBack()

        /**
         *  Fav Movie List
         */
//        Thread.sleep(1000) //Animasi
        onView(withText(movieTitle)).check(doesNotExist())
        onView(withId(R.id.fav_tab))
            .check(matches(isDisplayed()))

        onView(allOf(withText("TV SHOW"),
            isDescendantOfA(withId(R.id.fav_tab)))).perform(click())


        /**
         *  Fav Tvshow List
         */
        onView(withText(tvTitle)).check(matches(isDisplayed()))
        onView(withText(tvTitle)).perform(click())

        /**
         *  Tvshow Detail
         */
        onView(withId(R.id.tv_show_detail_poster)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tv_detail_backdrop)).perform(ViewActions.swipeUp())
        onView(withId(R.id.tv_show_detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_tv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.fab_tv)).perform(click())
        onView(withText("Removed from Favorite"))
            .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView))))
            .check(matches(isDisplayed()))
        Espresso.pressBack()

        /**
         * Fav Tvshow List
         */
//        Thread.sleep(1000) //Animasi
        onView(withText(tvTitle)).check(doesNotExist())
        onView(allOf(withText("MOVIE"),
            isDescendantOfA(withId(R.id.fav_tab)))).perform(click())

        onView(withId(R.id.navigation_dashboard)).perform(click())

        onView(withId(R.id.navigation_home)).perform(click())
    }

    private fun getText(viewInteraction: ViewInteraction): String? {
        val stringHolder = arrayOf<String?>(null)
        viewInteraction.perform(object : ViewAction {
            override fun getConstraints() = isAssignableFrom(TextView::class.java)

            override fun getDescription() = "Get text from View: ${stringHolder[0]}"

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView
                stringHolder[0] = tv.text.toString()
            }
        })
        return stringHolder[0]
    }
}