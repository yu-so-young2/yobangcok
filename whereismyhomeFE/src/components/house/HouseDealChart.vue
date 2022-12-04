<template>
  <div><canvas id="chart" ref="lineChart" height="200"></canvas></div>
</template>

<script>
import { mapState } from "vuex";
const houseStore = "houseStore";

import { Chart, registerables } from "chart.js";
// import { threadId } from "worker_threads";
Chart.register(...registerables);

export default {
  data() {
    return {
      myChart: null,
      labels: null,
      dataset: null,
    };
  },

  computed: {
    ...mapState(houseStore, ["housesDealList"]),
  },

  methods: {
    renderChart() {
      const ctx = document.getElementById("chart").getContext("2d");
      if (this.myChart != null) {
        this.myChart.destroy();
      }

      this.myChart = new Chart(ctx, {
        type: "line",
        data: {
          labels: this.labels,
          datasets: [
            {
              label: "월 평균 거래가",
              data: this.dataset,
              backgroundColor: ["rgba(255, 99, 132, 0.2)"],
              borderColor: ["rgba(255, 99, 132, 1)"],
              borderWidth: 1,
              // pointRadius: 0,
            },
          ],
        },
      });
    },
  },

  watch: {
    housesDealList: function () {
      console.log("아파트 정보 변경 감지");

      //dataset 변경
      var temp_labels = [];
      var temp_dataset = [];
      if (this.housesDealList.length >= 30) {
        var term = parseInt(this.housesDealList.length / 30);

        for (var i = this.housesDealList.length - 1; i >= 0; i -= term) {
          var deal_i = this.housesDealList[i];
          temp_labels.push(deal_i.dealYear + "/" + deal_i.dealMonth);
          temp_dataset.push(parseInt(deal_i.dealAmount.replace(",", "")));
        }
        this.labels = temp_labels;
        this.dataset = temp_dataset;
      } else {
        for (var j = this.housesDealList.length - 1; j >= 0; j--) {
          var deal_j = this.housesDealList[j];
          temp_labels.push(deal_j.dealYear + "/" + deal_j.dealMonth);
          temp_dataset.push(parseInt(deal_j.dealAmount.replace(",", "")));
        }
        this.labels = temp_labels;
        this.dataset = temp_dataset;
      }

      this.renderChart();
    },
  },
};
</script>

<style>
canvas {
  display: block;
  box-sizing: border-box;
  height: 160px;
  width: 300px;
  margin: 0px 40px;
  margin-bottom: 40px;
}
</style>
