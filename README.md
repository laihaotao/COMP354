# COMP 354 Pokemon Go Back

## Annoucement

*Always keep the new annoucement in the top*

- here is something I think maybe useful for our project, anyone can add material into this `README.md` file. ---by Eric

## Team Information

Name | Email address | Github UserName
-----|---------------|--------------
Frederic Desgreniers | FredericDesgreniers@gmail.com | FredericDesgreniers
Haotao(Eric) Lai | haotao.lai@gmail.com | LAIHAOTAO
Kawsara Munkalls | Kawsara25@yahoo.com | kawsara25
Martin Tseng | Martin.Tseng1988@gmail.com | martintseng
Prashanth Reddy | prashanth.abc369@gmail.com | prashanth369
Ruoshi wu | ruoshiwu1006@gmail.com
Xiaoyi Li | Li.xiaoyi2121@gmail.com

ps: *the order here only follow by the first character of the first name.*

## Some Useful Link

For documenters, we plan writre documentation in markdown's syntax. In case you are not familiar with markdown, here is a [link](https://guides.github.com/features/mastering-markdown/) to a quick guide of markdown.

For coder, we plan to use Maven for dependencies management. Also in case you didn't use it before, here is a [link](https://maven.apache.org/users/index.html) to the Maven user center, it is super easy to learn. By the way, in the project root directory you can find a file name `pom.xml`, this file is related to Maven.

For testing which is an important part to our project, we plan to use a famous testing framework called "JUnit". Here is a [link](https://github.com/junit-team/junit4/wiki/Getting-started) to JUnit website getting start part. I already added this framework(dependency) into the `pom.xml` file (Maven).

For buging tracking, we can just simply use the system provided by Github which called "issue". Here is a [link](https://guides.github.com/features/issues/) to a quick guide about it. I **strongerly suggest** all teams member should read it, since it is really significant.

ps: *If anyone who has difficulty in setup the environment please make a post in the Facebook group or contact others member who know how to do it.*

## Code Management and Coding Style

### Code Management

The whole project will use [Git](https://git-scm.com/) as version control system and all the code will be put in the Github as remote repository.

In order to achieve well management, we should make good use of [branch](https://git-scm.com/docs/git-branch), for good understand how it work we can see this [article](https://guides.github.com/introduction/flow/).

The basic idea is that everyone work in his own branch, when they finish their jobs we can merge them togerther. But before we merge, we need to make sure the code is "good".

In all the source code file, we should put the following information in the very begining of the file:

```java
/*
 * description:  brief introduction of the class function
 * author(s):    who wrote the code in this file
 * reviewer(s):  who review the code this file
 * date:         the date of create this file
 */
```

As we saw above, each file should be reviewed by other team member before it is merged into the main branch.

When the tester or reviewer find a bug in the code, please use the issue system to figure it out. In order to reproduce the bug, the person who make a new issure should provide as much possible as information for the author(s) to reproduce the error and using "@+author's Github username" to tell the author to deal with this issue (in Github '@' named mention).

### Code Style

In order to make the code clean, clear and easy to read, the whole team should follow only one code style. I suggest that we can use the [Google Java Code Style](https://google.github.io/styleguide/javaguide.html).

ps:*strongerly suggest you guys use the most intelligent IDE which is Intellij instead of Eclipse, trust me you will love it when you begin to use it!*
this is the pokemon rulebook if you want to check it.
http://assets.pokemon.com//assets/cms2/pdf/trading-card-game/rulebook/sm2_rulebook_en.pdf
