<template>
  <!-- eslint-disable -->
  <div class="calculator acrylic shadow">
    <div class="top">
      <p
        class="result"
        :class="{
          big: $store.state.result.length < 11,
          small: $store.state.result.length > 12 && $store.state.result.length < 16,
          tiny: $store.state.result.length > 15
        }"
        @click="result += 1"
      >
        {{ resultWithDots }}
      </p>
    </div>
    <div class="bottom">
      <Button symbol="x²" request="²" />
      <Button symbol="c" />
      <Button symbol="←" />
      <Button symbol="÷" request="/" />
      <Button symbol="7" strong />
      <Button symbol="8" strong />
      <Button symbol="9" strong />
      <Button symbol="×" request="*" />
      <Button symbol="4" strong />
      <Button symbol="5" strong />
      <Button symbol="6" strong />
      <Button symbol="-" />
      <Button symbol="1" strong />
      <Button symbol="2" strong />
      <Button symbol="3" strong />
      <Button symbol="+" />
      <Button symbol="±" />
      <Button symbol="0" strong />
      <Button symbol="," />
      <Button symbol="=" />
    </div>
  </div>
  
</template>

<script>
import Button from "./Button.vue";
import { FluentRevealEffect } from "fluent-reveal-effect";

export default {
  name: "calculator",
  components: {
    Button
  },
  data() {
    return {
      result: "1"
    };
  },
  computed: {
    resultWithDots() {
      var result = this.$store.state.result;
      var resultWithDots = "";

      var afterComma = result.split(",")[1];
      result = result.split(",")[0];

      var negative = false;
      if (result.charAt(0) == "-") {
        negative = true;
        result = result.split("-")[1];
      }

      while (result.length > 0) {
        if ((resultWithDots.length + 1) % 4 == 0) {
          resultWithDots = "." + resultWithDots;
        }
        resultWithDots = result.charAt(result.length - 1) + resultWithDots;
        result = result.slice(0, -1);
      }

      if (afterComma != null) {
        resultWithDots += "," + afterComma;
      }
      if (negative) {
        resultWithDots = "-" + resultWithDots;
      }
      return resultWithDots;
    }
  },
  mounted() {
    var calculator = document.getElementsByClassName("calculator")[0];

    if (document.hasFocus()) {
      calculator.classList.add("focused");
    }

    window.addEventListener(
      "focus",
      function() {
        calculator.classList.add("focused");
      },
      false
    );

    window.addEventListener(
      "blur",
      function() {
        calculator.classList.remove("focused");
      },
      false
    );

    FluentRevealEffect.applyEffect(".calculator", {
      clickEffect: true,
      lightColor: "rgba(255,255,255,0.6)",
      gradientSize: 80,
      isContainer: true,
      children: {
        borderSelector: ".btn-border",
        elementSelector: ".btn-highlight",
        lightColor: "rgba(255,255,255,0.3)",
        gradientSize: 150
      }
    });
  }
};
</script>

<style scoped>
/* eslint-disable */
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400);

.result {
  margin-right: 2vh;
  text-align: right;
  font-size: 2.5em;
}

.big {
  font-size: 3em;
}

.small {
  font-size: 2.15em;
}

.tiny {
  font-size: 1.9em;
}

.bottom {
  height: 73.125%;
  width: 97.5%;
  position: absolute;
  display: flex;
  flex-wrap: wrap;
  top: 25%;
  padding: 1.25%;
  z-index: 2;
}

.top {
  height: 25%;
  width: 100%;
  position: absolute;
  z-index: 2;
}

.calculator {
  width: 53.15vh;
  height: 66.1vh;
  min-width: 322px;
  min-height: 400px;

  border-style: solid;
  border-width: 1px;
  border-color: rgba(255, 255, 255, 0.3);
  user-select: none;
  z-index: 1;

  margin: auto;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}

.acrylic {
  overflow: hidden;
}

.acrylic::before {
  filter: blur(10px);
  content: "";
  z-index: 0;

  position: absolute;
  width: 100%;
  height: 100%;
}

.acrylic::after {
  content: "";
  z-index: 0;
  opacity: 1;
  background: rgb(31, 31, 31);

  position: absolute;
  width: 100%;
  height: 100%;
}

.acrylic.focused::after {
  opacity: 0.8;
  background-image: url(../assets/acrylic.png);
  background-blend-mode: overlay;
}

.shadow {
  border-radius: 1px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1), 0 1px 8px rgba(0, 0, 0, 0.2);
}
</style>
