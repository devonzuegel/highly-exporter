# highly-exporter

[HighlyTM](https://www.highly.co/) is shutting down, so I built a little tool to extract my prosthetic brain from their database before it gets deleted.

This script will help you export a large collection out of Highly. Necessary because their standard export tool to export it to CSV caps out at 1000 highlights.

## Usage

Open `core.clj` and add your token to the `YOUR-TOKEN` variable. Then, run `lein run`. If you have a big collection (I had 29k highlights to export), it can take a few minutes. It'll spit out a report that looks like this when it's done:

```
325 pages of JSON results processed
Found 28989 highlights across 3234 articles
Printed articles to JSON file: results.json
```

And you can find the results in `results.json` at the top of this directory.

## Requirements

You'll need to install [Leiningen](https://leiningen.org) at the very least to run this project. To be honest, I don't remember what all the other dependencies are (if any), but since HighlyTM gave us such a short window to pull out our data I figured it's more important that I get this out there quickly than write perfect documentation.

## Running the project

```sh
lein run  # Runs main from core.clj
lein repl # Opens the repl
```

## Notes to self

Use Calva to connect to a running REPL server in the project.

Use the `Load the current namespace in REPL window` command:

```js
{
  // This shortcut is custom to my setup.
  // I'm just putting it here for my own reference. :)
  "key": "shift+cmd+r",
  "command": "calva.loadNamespace",
  "when": "calva:connected"
}
```

The return value of the last expression can be found in the REPL with `*1`.