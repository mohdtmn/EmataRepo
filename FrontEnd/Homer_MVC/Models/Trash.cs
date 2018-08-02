namespace Homer_MVC.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    public partial class Trash
    {
        public int Id { get; set; }

        public double Xloc { get; set; }

        public double Yloc { get; set; }

        public int Level { get; set; }
    }
}
