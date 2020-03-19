package com.erfolgi.moviecataloguepro.util


import com.erfolgi.moviecataloguepro.data.source.local.entity.MovieEntity
import com.erfolgi.moviecataloguepro.data.source.local.entity.TVEntity
import com.erfolgi.moviecataloguepro.data.source.remote.response.detailMovieResponse.DetailMovieResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.detailMovieResponse.SpokenLanguagesItem
import com.erfolgi.moviecataloguepro.data.source.remote.response.detailTvResponse.DetailTvResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.movieResponse.MovieResponse
import com.erfolgi.moviecataloguepro.data.source.remote.response.movieResponse.ResultsItem
import com.erfolgi.moviecataloguepro.data.source.remote.response.tvResponse.TvItem
import com.erfolgi.moviecataloguepro.data.source.remote.response.tvResponse.TvResponse

class DataDummy {
    companion object{
        fun generateDummyMovies(): ArrayList<MovieEntity> {
            val results = ArrayList<MovieEntity>()
            results.add(MovieEntity("After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.",
                "en",
                "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                false,
                "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",

                "/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg",
                "/jiqD14fg7UTZOT6qgvzTmfRYpWI.jpg",
                "2020-02-05",
                293.633,
                7.1,
                495764,
                false,
                140))



            results.add(MovieEntity("A World War II satire that follows a lonely German boy whose world view is turned upside down when he discovers his single mother is hiding a young Jewish girl in their attic. Aided only by his idiotic imaginary friend, Adolf Hitler, Jojo must confront his blind nationalism.",
                "en",
                "Jojo Rabbit",
                false,
                "Jojo Rabbit",

                "/7GsM4mtM0worCtIVeiQt28HieeN.jpg",
                "/agoBZfL1q5G79SD0npArSlJn8BH.jpg",
                "2019-10-18",
                219.125,
                8.1,
                515001,
                false,
                1358))

            results.add(MovieEntity("At the height of the First World War, two young British soldiers, Schofield and Blake are given a seemingly impossible mission. In a race against time, they must cross enemy territory and deliver a message that will stop a deadly attack on hundreds of soldiers—Blake's own brother among them.",
                "en",
                "1917",
                false,
                "1917",

                "/iZf0KyrE25z1sage4SYFLCCrMi9.jpg",
                "/2WgieNR1tGHlpJUsolbVzbUbE1O.jpg",
                "2019-12-10",
                164.955,
                8.1,
                530915,
                false,
                2103))

            results.add(MovieEntity("Marcus and Mike are forced to confront new threats, career changes, and midlife crises as they join the newly created elite team AMMO of the Miami police department to take down the ruthless Armando Armas, the vicious leader of a Miami drug cartel.",
                "en",
                "Bad Boys for Life",
                false,
                "Bad Boys for Life",

                "/y95lQLnuNKdPAzw9F9Ab8kJ80c3.jpg",
                "/upUy2QhMZEmtypPW3PdieKLAHxh.jpg",
                "2020-01-15",
                164.433,
                6.5,
                38700,
                false,
                625))

            results.add(MovieEntity("American car designer Carroll Shelby and the British-born driver Ken Miles work together to battle corporate interference, the laws of physics, and their own personal demons to build a revolutionary race car for Ford Motor Company and take on the dominating race cars of Enzo Ferrari at the 24 Hours of Le Mans in France in 1966.",
                "en",
                "Ford v Ferrari",
                false,
                "Ford v Ferrari",

                "/6ApDtO7xaWAfPqfi2IARXIzj8QS.jpg",
                "/n3UanIvmnBlH531pykuzNs4LbH6.jpg",
                "2019-11-13",
                126.22,
                7.7,
                359724,
                false,
                1292))

            results.add(MovieEntity("An embattled NYPD detective, is thrust into a citywide manhunt for a pair of cop killers after uncovering a massive and unexpected conspiracy. As the night unfolds, lines become blurred on who he is pursuing, and who is in pursuit of him.",
                "en",
                "21 Bridges",
                false,
                "21 Bridges",

                "/bSN9SysoRBMwJKNkfOlQlCw2YQO.jpg",
                "/mXdTWBU9QqeTb4bupf5qCPnW393.jpg",
                "2019-10-24",
                109.662,
                6.6,
                535292,
                false,
                159))

            results.add(MovieEntity("A charismatic New York City jeweler always on the lookout for the next big score makes a series of high-stakes bets that could lead to the windfall of a lifetime. Howard must perform a precarious high-wire act, balancing business, family, and encroaching adversaries on all sides in his relentless pursuit of the ultimate win.",
                "en",
                "Uncut Gems",
                false,
                "Uncut Gems",

                "/7gOozJufKJ9WjcIs38KEs08Iq3D.jpg",
                "/uzvT6tYrU5SxfHe1ieimIGAqyFm.jpg",
                "2019-11-14",
                108.952,
                7.4,
                473033,
                false,
                652))

            results.add(MovieEntity("All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
                "ko",
                "기생충",
                false,
                "Parasite",

                "/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                "/TU9NIjwzjoKPwQHoHshkFcQUCG.jpg",
                "2019-05-30",
                130.907,
                8.6,
                496243,
                false,
                3195))

            results.add(MovieEntity("After losing his wife seven years earlier, the eccentric Dr. John Dolittle, famed doctor and veterinarian of Queen Victoria’s England, hermits himself away behind the high walls of Dolittle Manor with only his menagerie of exotic animals for company. But when the young queen falls gravely ill, a reluctant Dolittle is forced to set sail on an epic adventure to a mythical island in search of a cure, regaining his wit and courage as he crosses old adversaries and discovers wondrous creatures.",
                "en",
                "Dolittle",
                false,
                "Dolittle",

                "/hrraiBBuhu5E50otrtrykNqnOt5.jpg",
                "/lG802rseTZcN9mtLsQPVfApEVzM.jpg",
                "2020-01-01",
                128.871,
                6.4,
                448119,
                false,
                202))

            results.add(MovieEntity("Following the death of his wife, Ip Man travels to San Francisco to ease tensions between the local kung fu masters and his star student, Bruce Lee, while searching for a better future for his son.",
                "cn",
                "葉問4",
                false,
                "Ip Man 4: The Finale",

                "/yJdeWaVXa2se9agI6B4mQunVYkB.jpg",
                "/ekP6EVxL81lZ4ivcqPsoZ72rY0h.jpg",
                "2019-12-20",
                103.04,
                6.0,
                449924,
                false,
                236))

            return results
        }

        fun generateDummyMovieResponse(): MovieResponse {
            val results = ArrayList<ResultsItem>()
            results.add(ResultsItem("After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.",
                "en",
                "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                false,
                "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                null,
                "/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg",
                "/jiqD14fg7UTZOT6qgvzTmfRYpWI.jpg",
                "2020-02-05",
                293.633,
                7.1,
                495764,
                false,
                140))



            results.add(ResultsItem("A World War II satire that follows a lonely German boy whose world view is turned upside down when he discovers his single mother is hiding a young Jewish girl in their attic. Aided only by his idiotic imaginary friend, Adolf Hitler, Jojo must confront his blind nationalism.",
                "en",
                "Jojo Rabbit",
                false,
                "Jojo Rabbit",
                null,
                "/7GsM4mtM0worCtIVeiQt28HieeN.jpg",
                "/agoBZfL1q5G79SD0npArSlJn8BH.jpg",
                "2019-10-18",
                219.125,
                8.1,
                515001,
                false,
                1358))

            results.add(ResultsItem("At the height of the First World War, two young British soldiers, Schofield and Blake are given a seemingly impossible mission. In a race against time, they must cross enemy territory and deliver a message that will stop a deadly attack on hundreds of soldiers—Blake's own brother among them.",
                "en",
                "1917",
                false,
                "1917",
                null,
                "/iZf0KyrE25z1sage4SYFLCCrMi9.jpg",
                "/2WgieNR1tGHlpJUsolbVzbUbE1O.jpg",
                "2019-12-10",
                164.955,
                8.1,
                530915,
                false,
                2103))

            results.add(ResultsItem("Marcus and Mike are forced to confront new threats, career changes, and midlife crises as they join the newly created elite team AMMO of the Miami police department to take down the ruthless Armando Armas, the vicious leader of a Miami drug cartel.",
                "en",
                "Bad Boys for Life",
                false,
                "Bad Boys for Life",
                null,
                "/y95lQLnuNKdPAzw9F9Ab8kJ80c3.jpg",
                "/upUy2QhMZEmtypPW3PdieKLAHxh.jpg",
                "2020-01-15",
                164.433,
                6.5,
                38700,
                false,
                625))

            results.add(ResultsItem("American car designer Carroll Shelby and the British-born driver Ken Miles work together to battle corporate interference, the laws of physics, and their own personal demons to build a revolutionary race car for Ford Motor Company and take on the dominating race cars of Enzo Ferrari at the 24 Hours of Le Mans in France in 1966.",
                "en",
                "Ford v Ferrari",
                false,
                "Ford v Ferrari",
                null,
                "/6ApDtO7xaWAfPqfi2IARXIzj8QS.jpg",
                "/n3UanIvmnBlH531pykuzNs4LbH6.jpg",
                "2019-11-13",
                126.22,
                7.7,
                359724,
                false,
                1292))

            results.add(ResultsItem("An embattled NYPD detective, is thrust into a citywide manhunt for a pair of cop killers after uncovering a massive and unexpected conspiracy. As the night unfolds, lines become blurred on who he is pursuing, and who is in pursuit of him.",
                "en",
                "21 Bridges",
                false,
                "21 Bridges",
                null,
                "/bSN9SysoRBMwJKNkfOlQlCw2YQO.jpg",
                "/mXdTWBU9QqeTb4bupf5qCPnW393.jpg",
                "2019-10-24",
                109.662,
                6.6,
                535292,
                false,
                159))

            results.add(ResultsItem("A charismatic New York City jeweler always on the lookout for the next big score makes a series of high-stakes bets that could lead to the windfall of a lifetime. Howard must perform a precarious high-wire act, balancing business, family, and encroaching adversaries on all sides in his relentless pursuit of the ultimate win.",
                "en",
                "Uncut Gems",
                false,
                "Uncut Gems",
                null,
                "/7gOozJufKJ9WjcIs38KEs08Iq3D.jpg",
                "/uzvT6tYrU5SxfHe1ieimIGAqyFm.jpg",
                "2019-11-14",
                108.952,
                7.4,
                473033,
                false,
                652))

            results.add(ResultsItem("All unemployed, Ki-taek's family takes peculiar interest in the wealthy and glamorous Parks for their livelihood until they get entangled in an unexpected incident.",
                "ko",
                "기생충",
                false,
                "Parasite",
                null,
                "/7IiTTgloJzvGI1TAYymCfbfl3vT.jpg",
                "/TU9NIjwzjoKPwQHoHshkFcQUCG.jpg",
                "2019-05-30",
                130.907,
                8.6,
                496243,
                false,
                3195))

            results.add(ResultsItem("After losing his wife seven years earlier, the eccentric Dr. John Dolittle, famed doctor and veterinarian of Queen Victoria’s England, hermits himself away behind the high walls of Dolittle Manor with only his menagerie of exotic animals for company. But when the young queen falls gravely ill, a reluctant Dolittle is forced to set sail on an epic adventure to a mythical island in search of a cure, regaining his wit and courage as he crosses old adversaries and discovers wondrous creatures.",
                "en",
                "Dolittle",
                false,
                "Dolittle",
                null,
                "/hrraiBBuhu5E50otrtrykNqnOt5.jpg",
                "/lG802rseTZcN9mtLsQPVfApEVzM.jpg",
                "2020-01-01",
                128.871,
                6.4,
                448119,
                false,
                202))

            results.add(ResultsItem("Following the death of his wife, Ip Man travels to San Francisco to ease tensions between the local kung fu masters and his star student, Bruce Lee, while searching for a better future for his son.",
                "cn",
                "葉問4",
                false,
                "Ip Man 4: The Finale",
                null,
                "/yJdeWaVXa2se9agI6B4mQunVYkB.jpg",
                "/ekP6EVxL81lZ4ivcqPsoZ72rY0h.jpg",
                "2019-12-20",
                103.04,
                6.0,
                449924,
                false,
                236))

            return MovieResponse(null,1,1,results,1)
        }

        fun generateDummyTvShows():ArrayList<TVEntity>{
            val tvShows = ArrayList<TVEntity>()

            tvShows.add(
                TVEntity("2014-10-07",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "en",

                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",

                    "/6ZdQTBy20HzWudZthAV7NkZWfIb.jpg",
                    "The Flash",
                    174.095,
                    6.8,
                    "The Flash",
                    60735,
                    3225))

            tvShows.add(TVEntity("2005-09-22",
                "An elite team of FBI profilers analyze the country's most twisted criminal minds, anticipating their next moves before they strike again. The Behavioral Analysis Unit's most experienced agent is David Rossi, a founding member of the BAU who returns to help the team solve new cases.",
                "en",

                "/7TCwgX7oQKxcWYEhSPRmaHe6ULN.jpg",

                "/mzyLxbwy5jOveyfsbC1OWGp78NV.jpg",
                "Criminal Minds",
                170.135,
                7.4,
                "Criminal Minds",
                4057,
                751))

            tvShows.add(TVEntity("2009-09-23",
                "The Pritchett-Dunphy-Tucker clan is a wonderfully large and blended family. They give us an honest and often hilarious look into the sometimes warm, sometimes twisted, embrace of the modern family.",
                "en",

                "/fCiOen2bpru5JhzlaNkaNWNd9eP.jpg",

                "/x4lxFIhhrDI4nWtV8osnYwbGESV.jpg",
                "Modern Family",
                62.391,
                7.3,
                "Modern Family",
                1421,
                962))

            tvShows.add(TVEntity("2003-09-23",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "en",

                "/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg",

                "/4VuCtYBvZGq6Rk3gloigwlsTefE.jpg",
                "NCIS",
                83.331,
                6.9,
                "NCIS",
                4614,
                933))

            tvShows.add(TVEntity("2016-01-21",
                "When heroes alone are not enough ... the world needs legends. Having seen the future, one he will desperately try to prevent from happening, time-traveling rogue Rip Hunter is tasked with assembling a disparate group of both heroes and villains to confront an unstoppable threat — one in which not only is the planet at stake, but all of time itself. Can this ragtag team defeat an immortal threat unlike anything they have ever known?",
                "en",

                "/yJ3xE11IDIe29LJsSbhzwt5Oxtd.jpg",

                "/xOcuDnxTTqNT2CuFNy5VVc67tG4.jpg",
                "DC's Legends of Tomorrow",
                55.505,
                6.5,
                "DC's Legends of Tomorrow",
                62643,
                874))

            tvShows.add(TVEntity("2015-12-16",
                "Brakebills University is a secret institution specializing in magic. There, amidst an unorthodox education of spellcasting, a group of twenty-something friends soon discover that a magical fantasy world they read about as children is all too real— and poses grave danger to humanity.",
                "en",

                "/A66dZN98BPEYeFQAhNdNCIXa57d.jpg",

                "/wcfErhzLmmeutxAMpbHlR0Naa2q.jpg",
                "The Magicians",
                45.801,
                7.2,
                "The Magicians",
                64432,
                454))

            tvShows.add(TVEntity("2020-02-11",
                "A prisoner becomes a lawyer, litigating cases for other inmates while fighting to overturn his own life sentence for a crime he didn’t commit.",
                "en",

                "/2zh9v2G29RxwdRsbhSZK42ccfiQ.jpg",

                "/AcIQBuVJQlvToIHRXLsSxyNyKTU.jpg",
                "For Life",
                24.868,
                9.3,
                "For Life",
                92053,
                10))

            tvShows.add(TVEntity("2019-09-25",
                "Dex Parios is a strong, assertive, and unapologetically sharp-witted Army veteran working as a P.I. in Portland, Oregon. With a complicated personal history and only herself to rely on, she solves other people’s messes with a blind eye toward her own.",
                "en",

                "/3KphzAbThulphwHGCu22RzFlhqR.jpg",

                "/jFeeyKToJZZ0mRt8zerCDg0LdF5.jpg",
                "Stumptown",
                14.403,
                8.1,
                "Stumptown",
                89440,
                29))

            tvShows.add(TVEntity("2016-09-20",
                "Follows the lives and families of three adults living and growing up in the United States of America in present and past times. As their paths cross and their life stories intertwine in curious ways, we find that several of them share the same birthday - and so much more than anyone would expect.",
                "en",

                "/9oYpkVSbjX8TXifdAW0a5ek8XsW.jpg",

                "/shJhgNuC2a4hPdqvm8IDXFvYwLx.jpg",
                "This Is Us",
                41.393,
                8.0,
                "This Is Us",
                67136,
                395))

            tvShows.add(TVEntity("2019-06-12",
                "The story of a Ayse, who due to slanderous accusations is forced to marry Kerem, a well-known rich and handsome playboy.",
                "tr",

                "/6p3rWulvjNx1bP2xgqGxUh9bOCY.jpg",

                "/eKzdzg9ou6Hj34EmC0CcHFszCUy.jpg",
                "Afili Ask",
                39.781,
                5.3,
                "Afili Ask",
                89671,
                2))
            return tvShows
        }

        fun generateDummyTvShowResponse():TvResponse{
            val tvShows = ArrayList<TvItem>()

            tvShows.add(
                TvItem("2014-10-07",
                    "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                    "en",
                    null,
                    "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                    null,
                    "/6ZdQTBy20HzWudZthAV7NkZWfIb.jpg",
                    "The Flash",
                    174.095,
                    6.8,
                    "The Flash",
                    60735,
                    3225))

            tvShows.add(TvItem("2005-09-22",
                "An elite team of FBI profilers analyze the country's most twisted criminal minds, anticipating their next moves before they strike again. The Behavioral Analysis Unit's most experienced agent is David Rossi, a founding member of the BAU who returns to help the team solve new cases.",
                "en",
                null,
                "/7TCwgX7oQKxcWYEhSPRmaHe6ULN.jpg",
                null,
                "/mzyLxbwy5jOveyfsbC1OWGp78NV.jpg",
                "Criminal Minds",
                170.135,
                7.4,
                "Criminal Minds",
                4057,
                751))

            tvShows.add(TvItem("2009-09-23",
                "The Pritchett-Dunphy-Tucker clan is a wonderfully large and blended family. They give us an honest and often hilarious look into the sometimes warm, sometimes twisted, embrace of the modern family.",
                "en",
                null,
                "/fCiOen2bpru5JhzlaNkaNWNd9eP.jpg",
                null,
                "/x4lxFIhhrDI4nWtV8osnYwbGESV.jpg",
                "Modern Family",
                62.391,
                7.3,
                "Modern Family",
                1421,
                962))

            tvShows.add(TvItem("2003-09-23",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "en",
                null,
                "/fi8EvaWtL5CvoielOjjVvTr7ux3.jpg",
                null,
                "/4VuCtYBvZGq6Rk3gloigwlsTefE.jpg",
                "NCIS",
                83.331,
                6.9,
                "NCIS",
                4614,
                933))

            tvShows.add(TvItem("2016-01-21",
                "When heroes alone are not enough ... the world needs legends. Having seen the future, one he will desperately try to prevent from happening, time-traveling rogue Rip Hunter is tasked with assembling a disparate group of both heroes and villains to confront an unstoppable threat — one in which not only is the planet at stake, but all of time itself. Can this ragtag team defeat an immortal threat unlike anything they have ever known?",
                "en",
                null,
                "/yJ3xE11IDIe29LJsSbhzwt5Oxtd.jpg",
                null,
                "/xOcuDnxTTqNT2CuFNy5VVc67tG4.jpg",
                "DC's Legends of Tomorrow",
                55.505,
                6.5,
                "DC's Legends of Tomorrow",
                62643,
                874))

            tvShows.add(TvItem("2015-12-16",
                "Brakebills University is a secret institution specializing in magic. There, amidst an unorthodox education of spellcasting, a group of twenty-something friends soon discover that a magical fantasy world they read about as children is all too real— and poses grave danger to humanity.",
                "en",
                null,
                "/A66dZN98BPEYeFQAhNdNCIXa57d.jpg",
                null,
                "/wcfErhzLmmeutxAMpbHlR0Naa2q.jpg",
                "The Magicians",
                45.801,
                7.2,
                "The Magicians",
                64432,
                454))

            tvShows.add(TvItem("2020-02-11",
                "A prisoner becomes a lawyer, litigating cases for other inmates while fighting to overturn his own life sentence for a crime he didn’t commit.",
                "en",
                null,
                "/2zh9v2G29RxwdRsbhSZK42ccfiQ.jpg",
                null,
                "/AcIQBuVJQlvToIHRXLsSxyNyKTU.jpg",
                "For Life",
                24.868,
                9.3,
                "For Life",
                92053,
                10))

            tvShows.add(TvItem("2019-09-25",
                "Dex Parios is a strong, assertive, and unapologetically sharp-witted Army veteran working as a P.I. in Portland, Oregon. With a complicated personal history and only herself to rely on, she solves other people’s messes with a blind eye toward her own.",
                "en",
                null,
                "/3KphzAbThulphwHGCu22RzFlhqR.jpg",
                null,
                "/jFeeyKToJZZ0mRt8zerCDg0LdF5.jpg",
                "Stumptown",
                14.403,
                8.1,
                "Stumptown",
                89440,
                29))

            tvShows.add(TvItem("2016-09-20",
                "Follows the lives and families of three adults living and growing up in the United States of America in present and past times. As their paths cross and their life stories intertwine in curious ways, we find that several of them share the same birthday - and so much more than anyone would expect.",
                "en",
                null,
                "/9oYpkVSbjX8TXifdAW0a5ek8XsW.jpg",
                null,
                "/shJhgNuC2a4hPdqvm8IDXFvYwLx.jpg",
                "This Is Us",
                41.393,
                8.0,
                "This Is Us",
                67136,
                395))

            tvShows.add(TvItem("2019-06-12",
                "The story of a Ayse, who due to slanderous accusations is forced to marry Kerem, a well-known rich and handsome playboy.",
                "tr",
                null,
                "/6p3rWulvjNx1bP2xgqGxUh9bOCY.jpg",
                null,
                "/eKzdzg9ou6Hj34EmC0CcHFszCUy.jpg",
                "Afili Ask",
                39.781,
                5.3,
                "Afili Ask",
                89671,
                2))
            return TvResponse(1,1,tvShows,1)
        }

        fun generateDummyMovieDetail():MovieEntity{
            return MovieEntity("After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.",
                "en",
                "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                false,
                "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",

                "/h4VB6m0RwcicVEZvzftYZyKXs6K.jpg",
                "/jiqD14fg7UTZOT6qgvzTmfRYpWI.jpg",
                "2020-02-05",
                293.633,
                7.1,
                495764,
                false,
                140)
        }

        fun generateDummyMovieDetailResponse():DetailMovieResponse{
            return DetailMovieResponse("en",
                "tt7713068",
                false,
                "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                "/uozb2VeD87YmhoUP1RrGWfzuCrr.jpg",
                145273302,
                null,
                168.698,
                null,
                495764,
                633,
                75000000,
                "After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.",
                "Birds of Prey (and the Fantabulous Emancipation of One Harley Quinn)",
                109,
                "/nx0IFupVrlrgKncaza2aUascED6.jpg",
                listOf(SpokenLanguagesItem("en","English")),
                null,
                "2020-02-05",
                7.0,
                null,
                "Mind Over Mayhem",
                false,
                "http://www.birdsofpreymovie.net/",
                "Released")
        }

        fun generateDummyTvShowDetail():TVEntity{
            return TVEntity("2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "en",

                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",

                "/6ZdQTBy20HzWudZthAV7NkZWfIb.jpg",
                "The Flash",
                174.095,
                6.8,
                "The Flash",
                60735,
                3225)
        }

        fun generateDummyTvShowDetailResponse():DetailTvResponse{
            return DetailTvResponse("en",
                129,
                null,
                "Scripted",
                "/6ZdQTBy20HzWudZthAV7NkZWfIb.jpg",
                null,
                174.095,
                60735,
                6,
                0,
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                null,
                null,
                null,
                null,
                "/wHa6KOJAoNTFLFtp7wguUJKSnju.jpg",
                null,
                null,
                "The Flash",
                6.8,
                "The Flash",
                null,
                null,
                true,
                "2020-02-11",
                "http://www.cwtv.com/shows/the-flash/",
                "Returning Series")
        }

        fun getMovie(movieId:String): MovieEntity? {
            for(i in 0..(generateDummyMovies().size )){
                val entity= generateDummyMovies()[i]
                if(entity.id ==(movieId.toInt())){
                    return entity
                }
            }
            return null
        }

        fun getTvShow(tvShowId:String): TVEntity? {
            for(i in 0..generateDummyTvShows().size){
                val entity = generateDummyTvShows()[i]
                if(entity.id==(tvShowId.toInt())){
                    return entity
                }
            }
            return null
        }
    }


}