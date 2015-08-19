# pict-source

The programmers open dictionary from programmers for culture of using your own spoken language.

## Usage

Just visit [http://pict.divshot.io](http://pict.divshot.io).

### Localy

1. Clone the repo
2. `$ cd pict-source`
3. Install dependencies `$ lein deps`
4. Run tests `$ lein test`
5. Run a web server `$ lein ring server` (it will open localhost:3000 in your browser)

## Contribution

You can add new features or those [features that I would like to implement](https://github.com/shvetsovdm/pict-source#license),
contribute to the project documentation, fix typos or contribyte to Languages Dictionary itself.
To add new you can use shell script `.bin/pict :lang :word :translation` for example `./bin/pict ru code_review 'Обзор кода'` in order to create word json file
or you have to create new JSON file manually with following path `resources/dictionary/:language/:first-letter/:word.json`.

Contribution process:

1. Fork the [official repository](https://github.com/shvetsovdm/pict-source).
2. Make your changes in a topic branch.
3. Send a pull request.

How to contribute in general:

1. [Contributing to Open Source on GitHub](https://guides.github.com/activities/contributing-to-open-source/) guide.
2. [Using pull requests](https://help.github.com/articles/using-pull-requests/) Github help.

## Features that I would like to implement

0. Improve design and testability of the code
1. Logotype and website icons.
2. Abbreviations and aliases for dictionary words.
3. Made with (in the site footer) clojure, divshot, stasis and hiccup icons.
4. Keywords, description, nofollow, noidex meta tags for pages.
5. Categories and/or tags for dictionary words.
6. Wikipedia links for dictionary words.
7. Source links for dictionary words.
8. Add SASS support.
9. Executable for effortless creation words and dictionaries via CLI.
10. Abandon Skeleton framework.

## License

Copyright © 2015 Dmitry Shvetsov (@shvetsovdm)

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
