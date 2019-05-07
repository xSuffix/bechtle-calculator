import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const url = "http://localhost:8081/api";

function sendInput(input) {
  var response = fetch(url, {
    headers: {
      "undefinedaccept": "application/json",
      "accept-encoding": "gzip, deflate",
      "accept-language": "en-US,en;q=0.8",
      "content-type": "application/json",
      "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.108 Safari/537.36"
    },
    body: {
      "command": input
    },
    method: "POST"
  })
    .then(res => {
      console.log(res);
    })
    .catch(err => console.log(err));
  return response;
}

export default new Vuex.Store({
  state: {
    result: "0"
  },
  mutations: {
    appendSymbol(state, input) {
      console.log(input);

      if (!isNaN(input)) {
        if (state.result == "0") {
          state.result = input;
        } else {
          sendInput(input);
          state.result += input;
        }
      } else if (input == "←") {
        if (
          state.result.length < 2 ||
          (state.result.charAt(0) == "-" && state.result.length < 3)
        ) {
          state.result = "0";
        } else {
          state.result = state.result.slice(0, -1);
        }
      } else if (input == ",") {
        if (!state.result.includes(",")) {
          state.result += ",";
        }
      } else if (input == "=") {
        console.log("Backend =");
      } else if (input == "*") {
        console.log("Backend *");
      } else if (input == "+") {
        console.log("Backend +");
      } else if (input == "-") {
        console.log("Backend -");
      } else if (input == "/") {
        console.log("Backend /");
      } else if (input == "±") {
        if (state.result.charAt(0) == "-") {
          state.result = state.result.substr(1);
        } else if (state.result != "0") {
          state.result = "-" + state.result;
        }
      } else if (input == "c") {
        console.log("Backend c");
      }
    }
  },
  actions: {}
});
