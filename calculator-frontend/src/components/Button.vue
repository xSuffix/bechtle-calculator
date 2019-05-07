<template>
  <div class="btn" @click="enter(symbol)">
    <div class="btn-border">
      <div class="btn-center">
        <div class="btn-highlight"></div>
        <p class="symbol">{{ this.symbol }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Button",
  props: {
    strong: Boolean,
    symbol: String,
    request: String
  },
  methods: {
    enter(symbol) {
      if (this.request == null) {
        this.$store.commit("appendSymbol", symbol);
      } else {
        this.$store.commit("appendSymbol", this.request);
      }
    }
  },
  mounted() {
    if (this.strong) {
      this.$el.classList.add("strong");
    }
  }
};
</script>

<style scoped>
.btn-highlight {
  width: 100%;
  height: 100%;
  position: absolute;
  z-index: 5;
}

.symbol {
  font-size: 1.6em;
  margin: auto;
  z-index: 6;
  pointer-events: none;
}

.btn {
  width: 23.5%;
  height: 18%;
  margin: 0.75%;

  display: inline-flex;
  align-items: center;
  overflow: hidden;
  position: relative;
  z-index: 4;
}

.btn-center {
  display: flex;
  align-items: center;
  width: 95%;
  height: 92.5%;
  transition: all 0.1s ease;
  overflow: hidden;
  background-color: inherit;
  margin: auto;
  position: relative;
  z-index: 5;
}

.btn-center::before {
  content: "";
  filter: blur(10px);
  z-index: 4;

  position: absolute;
  width: 100%;
  height: 100%;
}

.btn-center::after {
  content: "";
  z-index: 4;
  background-color: rgb(19, 19, 19);
  position: absolute;
  margin: auto;
  width: 100%;
  height: 100%;
  opacity: 1;
}

.focused .btn-center::after {
  background-color: rgba(19, 19, 19, 0.74);
  background-image: url(../assets/acrylic.png);
  background-blend-mode: overlay;
}

.strong .btn-center::after {
  background-color: rgba(6, 6, 6, 0.83);
}

.btn-border {
  width: 100%;
  height: 100%;

  display: flex;
  align-items: center;
  position: relative;
  z-index: 4;
}

.btn::before {
  content: "";
  filter: blur(10px);
  z-index: 3;

  position: absolute;
  width: 100%;
  height: 100%;
}

.btn::after {
  content: "";
  z-index: 3;
  background-color: rgb(19, 19, 19);
  position: absolute;
  margin: auto;
  width: 100%;
  height: 100%;
  opacity: 0.95;
}

.focused .btn::after {
  background-color: rgba(19, 19, 19, 0.8);
  background-image: url(../assets/acrylic.png);
  background-blend-mode: overlay;
}

.strong::after {
  background-color: rgba(6, 6, 6);
}

.focused .strong::after {
  background-color: rgba(6, 6, 6, 0.9);
}
</style>
