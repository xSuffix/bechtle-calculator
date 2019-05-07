<template>
  <div id="app">
    <Calculator />
  </div>
</template>

<script>
import Calculator from "./components/Calculator.vue";

const KEYCODE_Backspace = 8;
const KEYCODE_Enter = 13;
const KEYCODE_0 = 48;
const KEYCODE_1 = 49;
const KEYCODE_2 = 50;
const KEYCODE_3 = 51;
const KEYCODE_4 = 52;
const KEYCODE_5 = 53;
const KEYCODE_6 = 54;
const KEYCODE_7 = 55;
const KEYCODE_8 = 56;
const KEYCODE_9 = 57;
const KEYCODE_Multiply = 106;
const KEYCODE_Add = 107;
const KEYCODE_Subtract = 109;
const KEYCODE_Divide = 111;
const KEYCODE_Plus = 187;
const KEYCODE_Comma = 188;
const KEYCODE_Dash = 189;

export default {
  name: "app",
  components: {
    Calculator
  },
  mounted() {
    window.addEventListener("keydown", e => {
      switch (e.keyCode) {
        case KEYCODE_Backspace:
          this.$store.commit("appendSymbol", "←");
          break;
        case KEYCODE_Enter:
          this.$store.commit("appendSymbol", "=");
          break;
        case KEYCODE_0:
          if (e.shiftKey) {
            this.$store.commit("appendSymbol", "=");
            break;
          }
          this.$store.commit("appendSymbol", "0");
          break;
        case KEYCODE_1:
          this.$store.commit("appendSymbol", "1");
          break;
        case KEYCODE_2:
          this.$store.commit("appendSymbol", "2");
          break;
        case KEYCODE_3:
          this.$store.commit("appendSymbol", "3");
          break;
        case KEYCODE_4:
          this.$store.commit("appendSymbol", "4");
          break;
        case KEYCODE_5:
          this.$store.commit("appendSymbol", "5");
          break;
        case KEYCODE_6:
          this.$store.commit("appendSymbol", "6");
          break;
        case KEYCODE_7:
          if (e.shiftKey) {
            this.$store.commit("appendSymbol", "/");
            break;
          }
          this.$store.commit("appendSymbol", "7");
          break;
        case KEYCODE_8:
          this.$store.commit("appendSymbol", "8");
          break;
        case KEYCODE_9:
          this.$store.commit("appendSymbol", "9");
          break;
        case KEYCODE_Multiply:
          this.$store.commit("appendSymbol", "*");
          break;
        case KEYCODE_Add:
          this.$store.commit("appendSymbol", "+");
          break;
        case KEYCODE_Subtract:
          this.$store.commit("appendSymbol", "-");
          break;
        case KEYCODE_Divide:
          this.$store.commit("appendSymbol", "/");
          break;
        case KEYCODE_Plus:
          if (e.shiftKey) {
            this.$store.commit("appendSymbol", "*");
            break;
          }
          this.$store.commit("appendSymbol", "+");
          break;
        case KEYCODE_Comma:
          this.$store.commit("appendSymbol", ",");
          break;
        case KEYCODE_Dash:
          this.$store.commit("appendSymbol", "-");
          break;
      }
    });
  }
};
</script>

<style>
p {
  color: #ffffff;
  font-family: "Open Sans", sans-serif;
}

#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

body {
  margin: 0;
  width: 100vw;
  height: 100vh;
}

body,
.acrylic::before {
  background: url(assets/wallpaper.jpg) center center fixed;
  background-size: cover;
}
</style>
