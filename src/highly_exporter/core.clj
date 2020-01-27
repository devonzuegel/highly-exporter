(ns highly-exporter.core
  (:require [clj-http.client :as http]
            [cheshire.core :as cheshire]
            [clojure.java.io :as io]))

(defn get-html [url] (http/get url))

; You can find this token in your Highly settings as
; "RefreshPrivate Access Key" in the Data section.
(def YOUR-TOKEN "Add your token here!")

(def highly-base-url (str "https://www.highly.co/access/highlights.json?token=" YOUR-TOKEN "&next_marker="))

(defn get-next-marker [parsed]
  (get parsed "next_marker"))

(defn get-parsed-result [next-marker]
  (cheshire/parse-string 
   (:body (get-html (str highly-base-url next-marker)))))

(defn is-final-page [n next-marker]
  ; (= n 3)) ; For testing smaller batches
  (and
   (> n 0) ; If it's the first page, then the next-marker will be nil even though we're not done
   (or (= next-marker nil) (= next-marker ""))))

(defn count-highlights [article]
  (count (get article "highlights")))

(defn count-all-highlights [results]
  (let [num-highlights (apply + (map count-highlights results))
        n-articles     (count results)]
    (println "Found" num-highlights "highlights across" n-articles "articles")))

(defn handle-results! [n-pages results]
  (println n-pages "pages of JSON results processed")
  (count-all-highlights results)
  (cheshire/generate-stream results (io/writer "results.json"))  
  (println "Printed articles to JSON file: results.json"))

(defn fetch-all [n next-marker results]
  (if (is-final-page n next-marker)
    (handle-results! n results) ; Return final results
    (let [parsed           (get-parsed-result next-marker)
          next-next-marker (get-next-marker parsed)
          new-results      (concat results (get parsed "articles"))]
      (fetch-all (inc n) next-next-marker new-results))))

(defn -main [] (fetch-all 0 "" []))
