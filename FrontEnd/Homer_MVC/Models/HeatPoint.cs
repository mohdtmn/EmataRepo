namespace Homer_MVC.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class HeatPoint
    {
        public int Id { get; set; }

        public double Xloc { get; set; }

        public double Yloc { get; set; }

        public int Type { get; set; }

        public DateTime instant { get; set; }
    }
}
