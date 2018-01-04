package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.EpoxyController
import com.github.jasonhezz.likesplash.data.ExplorePhoto
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.Tag
import com.github.jasonhezz.likesplash.data.model.exploreCarouselPhoto
import com.github.jasonhezz.likesplash.data.model.exploreCarouselTag
import com.github.jasonhezz.likesplash.data.model.exploreTitle

/**
 * Created by JavaCoder on 2018/1/3.
 */
class ExploreController : EpoxyController() {

  private val businessExplore = ExplorePhoto("Business",
      "Download free business photos of real people getting ready for work in real life. No cheesy or stocky business pictures here.",
      listOf(Tag("Office",
          "https://images.unsplash.com/photo-1495521939206-a217db9df264?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "An office desk with an iMac computer and keyboard next to a window in the sunlight."),
          Tag("Work",
              "https://images.unsplash.com/photo-1498622205843-3b0ac17f8ba4?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "A woman sits in front of a laptop computer working in her office with a cup of coffee next to her"),
          Tag("Startup",
              "https://images.unsplash.com/photo-1489506020498-e6c1cc350f10?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "A man with a watch types on a keyboard in front of his computer at a startup.")))

  private val girlExplore = ExplorePhoto("Girl",
      "Download diverse photos of girls with various emotions from laughter, discovery, and wonder.",
      listOf(Tag("Love",
          "https://images.unsplash.com/reserve/Af0sF2OS5S5gatqrKzVP_Silhoutte.jpg?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "A hand makes a silhoutted love sign in front of a sunset."),
          Tag("Man",
              "https://images.unsplash.com/photo-1497631388022-bb9fed46bb28?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "A woman sits in front of a laptop computer working in her office with a cup of coffee next to her"),
          Tag("Family",
              "https://images.unsplash.com/photo-1462598433584-d7ffa5a70489?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "A mother plays with her daughter and family in the sun.")))

  private val natureExplore = ExplorePhoto("Nature",
      "Travel to the scenic views with our most popular nature photos from rocky mountains to redwood forests. Discover and download these nature pictures for your blog or post to social media.",
      listOf(Tag("Flower",
          "https://images.unsplash.com/photo-1497308729380-0667ec2f5c32?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "A yellow flower on a black silhoutted background."),
          Tag("Sky",
              "https://images.unsplash.com/photo-1498496294664-d9372eb521f3?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "White clouds in the sky shine in the sun and the blue background of the sky."),
          Tag("Spring",
              "https://images.unsplash.com/photo-1491403727552-358a3512bab2?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "Spring pink petals from a tree set against a blue sky background.")))

  private val technologyExplore = ExplorePhoto("Technology",
      "Browse these technology images featuring workspaces fill with gadgets, MacBooks, iPhones, and cameras.",
      listOf(Tag("Computer",
          "https://images.unsplash.com/photo-1496674205429-924b32acd421?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "A Macbook computer with a keyboard."),
          Tag("Phone",
              "https://images.unsplash.com/photo-1501850305723-0bf18f354fea?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "A person holds an iPhone in their hand and taps the screen."),
          Tag("Macbook",
              "https://images.unsplash.com/photo-1473520844623-167ad716dcae?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "An Apple Macbook Pro computer with a backlight display.")))

  private val foodExplore = ExplorePhoto("Food",
      "Get hungry with these beautiful pictures of food. Download our most popular food images featuring coffee and pastries, fast food, vegan food, Thai food, and Mexican food.",
      listOf(Tag("Coffee",
          "https://images.unsplash.com/photo-1499961524705-bfd103e65a6d?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "A teal coffee cup with latte art in the shape of a heart."), Tag("Dessert",
          "https://images.unsplash.com/photo-1497681204192-eb12c0702305?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "A white cheesecake dessert with a fork."), Tag("Cake",
          "https://images.unsplash.com/photo-1464347744102-11db6282f854?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "A birthday cake with colored sprinkles.")))

  private val travelExplore = ExplorePhoto("Travel",
      "Get lost in our most popular travel photos featuring overseas adventures and backpacking journeys. Download these travel pictures to inspire your wanderlust.",
      listOf(Tag("Vacation",
          "https://images.unsplash.com/photo-1492979423821-7193e8938424?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "A tropical beach with people, beach umbrellas, and white sand."),
          Tag("New York",
              "https://images.unsplash.com/42/U7Fc1sy5SCUDIu4tlJY3_NY_by_PhilippHenzler_philmotion.de.jpg?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "Commercial buildings in downtown Manhatann New York show the NY skyline."),
          Tag("Iceland",
              "https://images.unsplash.com/photo-1490440677891-aed951b36020?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "A waterfall in Vik Iceland showers water from up high over the cliff edge.")))

  private val happyExplore = ExplorePhoto("Happy",
      "Celebrate happy days full of smiling and joy. Download these popular happy images perfect for birthdays, mother's day, valentines day, and anniversary.",
      listOf(Tag("Love",
          "https://images.unsplash.com/photo-1474401639975-278ecfd1b421?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "A man and a woman embrace lovingly inside a blanket in the sunlight."),
          Tag("Party",
              "https://images.unsplash.com/photo-1478145787956-f6f12c59624d?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "A party hat with multicoloured sprinkles and confetti laid out on a table."),
          Tag("Friends",
              "https://images.unsplash.com/photo-1491438590914-bc09fcaaf77a?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "A group of girl friends laugh closely.")))

  private val coolExplore = ExplorePhoto("Cool",
      "Download these cool animal pictures, cool nature pictures, and cool space pictures. Use these HD photos for your desktop wallpaper.",
      listOf(Tag("Space",
          "https://images.unsplash.com/photo-1487925876428-ebd8a23ee1d4?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
          "A beautiful view of the Milky Way and the stars in outer space."),
          Tag("Background",
              "https://images.unsplash.com/photo-1484589065579-248aad0d8b13?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "An abstract multicoloured background."),
          Tag("Wallpaper",
              "https://images.unsplash.com/photo-1495932574959-fa95bc3c4ac8?dpr=2&auto=format&crop=entropy&fit=crop&w=376&h=251&q=60&cs=tinysrgb",
              "A blue and white wave shot from underwater.")))

  var businessPhoto = emptyList<Photo>()
  var girlPhoto = emptyList<Photo>()
  var naturePhoto = emptyList<Photo>()
  var technologyPhoto = emptyList<Photo>()
  var foodPhoto = emptyList<Photo>()
  var travelPhoto = emptyList<Photo>()
  var happyPhoto = emptyList<Photo>()
  var coolPhoto = emptyList<Photo>()

  override fun buildModels() {
    exploreTitle {
      id("business_title")
      title(businessExplore.name)
      description(businessExplore.descriptionFragment)
    }
    exploreCarouselPhoto {
      id("business_photo")
      photos(businessPhoto)
    }
    exploreCarouselTag {
      id("business_tag")
      tags(businessExplore.related)
    }

    exploreTitle {
      id("girl_title")
      title(girlExplore.name)
      description(girlExplore.descriptionFragment)
    }
    exploreCarouselPhoto {
      id("girl_photo")
      photos(girlPhoto)
    }
    exploreCarouselTag {
      id("girl_tag")
      tags(girlExplore.related)
    }

    exploreTitle {
      id("nature_title")
      title(natureExplore.name)
      description(natureExplore.descriptionFragment)
    }
    exploreCarouselPhoto {
      id("nature_photo")
      photos(naturePhoto)
    }
    exploreCarouselTag {
      id("nature_tag")
      tags(natureExplore.related)
    }

    exploreTitle {
      id("technology_title")
      title(technologyExplore.name)
      description(technologyExplore.descriptionFragment)
    }
    exploreCarouselPhoto {
      id("technology_photo")
      photos(technologyPhoto)
    }
    exploreCarouselTag {
      id("technology_tag")
      tags(technologyExplore.related)
    }

    exploreTitle {
      id("food_title")
      title(foodExplore.name)
      description(foodExplore.descriptionFragment)
    }
    exploreCarouselPhoto {
      id("food_photo")
      photos(foodPhoto)
    }
    exploreCarouselTag {
      id("food_tag")
      tags(foodExplore.related)
    }

    exploreTitle {
      id("travel_title")
      title(travelExplore.name)
      description(travelExplore.descriptionFragment)
    }
    exploreCarouselPhoto {
      id("travel_photo")
      photos(travelPhoto)
    }
    exploreCarouselTag {
      id("travel_tag")
      tags(travelExplore.related)
    }

    exploreTitle {
      id("happy_title")
      title(happyExplore.name)
      description(happyExplore.descriptionFragment)
    }
    exploreCarouselPhoto {
      id("happy_photo")
      photos(happyPhoto)
    }
    exploreCarouselTag {
      id("happy_tag")
      tags(happyExplore.related)
    }

    exploreTitle {
      id("cool_title")
      title(coolExplore.name)
      description(coolExplore.descriptionFragment)
    }
    exploreCarouselPhoto {
      id("cool_photo")
      photos(coolPhoto)
    }
    exploreCarouselTag {
      id("cool_tag")
      tags(coolExplore.related)
    }
  }
}