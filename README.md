# highly-exporter

Export a large collection out of Highly. Necessary because their standard export tool to export it to CSV caps out at 1000 highlights.

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